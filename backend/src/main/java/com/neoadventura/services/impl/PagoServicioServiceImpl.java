package com.neoadventura.services.impl;

import com.neoadventura.dtos.PagoServicioDto;
import com.neoadventura.entities.Pago;
import com.neoadventura.entities.PagoServicio;
import com.neoadventura.entities.PagoServicioKey;
import com.neoadventura.entities.Servicio;
import com.neoadventura.exceptions.InternalServerErrorException;
import com.neoadventura.exceptions.NeoAdventuraException;
import com.neoadventura.exceptions.NotFoundException;
import com.neoadventura.repositories.PagoRepository;
import com.neoadventura.repositories.PagoServicioRepository;
import com.neoadventura.repositories.ServicioRepository;
import com.neoadventura.services.PagoServicioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PagoServicioServiceImpl implements PagoServicioService {

    @Autowired
    private PagoServicioRepository pagoServicioRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    private static final ModelMapper modelmapper= new ModelMapper();

//    @Override
//    public List<PagoServicioDto> getPagoServicios() throws NeoAdventuraException {
//        List<Review> reviewsEntity= pagoServicioRepository.findAll();
//        List<ReviewDto> reviewDtos = reviewsEntity.stream().map(x->modelmapper.map(x,ReviewDto.class))
//                .collect(Collectors.toList());
//        for (int i=0; i<reviewDtos.size();++i) {
//            reviewDtos.get(i).setServicio_id(reviewsEntity.get(i).getId().getServicioId());
//            reviewDtos.get(i).setUsuario_id(reviewsEntity.get(i).getId().getUsuarioId());
//        }
//        return reviewDtos;
//    }

    @Transactional
    @Override
    public PagoServicioDto CreatePagoServicio(PagoServicioDto pagoServicioDto) throws NeoAdventuraException {
        Pago pago = getPagoEntity(pagoServicioDto.getPago_id());

        Servicio servicio = getServicioEntity(pagoServicioDto.getServicio_id());


        PagoServicio pagoServicio=new PagoServicio(
                new PagoServicioKey(
                        pagoServicioDto.getPago_id(),
                        pagoServicioDto.getServicio_id()
                ),
                pago,
                servicio,
                pagoServicioDto.getReported(),
                pagoServicioDto.getDescription()
        );

        try {
            pagoServicio= pagoServicioRepository.save(pagoServicio);
        }catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelmapper.map(getPagoServicioEntity(pagoServicio.getPago().getId(), pagoServicio.getServicio().getId()), PagoServicioDto.class);
    }

    private Pago getPagoEntity(Long id) throws NeoAdventuraException {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOTFOUND-404", "PAGO_NOTFOUND-404"));
    }

    private Servicio getServicioEntity(Long id) throws NeoAdventuraException {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOTFOUND-404", "SERVICIO_NOTFOUND-404"));
    }

    private PagoServicio getPagoServicioEntity(Long pagoId, Long servicioId) throws NeoAdventuraException{
        return pagoServicioRepository.findByPagoAndServicio(getPagoEntity(pagoId), getServicioEntity(servicioId))
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","PAGO_SERVICIO_NOTFOUND-404"));
    }

}
