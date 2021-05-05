package com.neoadventura.services.impl;

import com.neoadventura.dtos.CreateServiceDto;
import com.neoadventura.dtos.ModalidadDto;
import com.neoadventura.dtos.ServicioDto;
import com.neoadventura.entities.*;
import com.neoadventura.exceptions.InternalServerErrorException;
import com.neoadventura.exceptions.NeoAdventuraException;
import com.neoadventura.exceptions.NotFoundException;
import com.neoadventura.repositories.*;
import com.neoadventura.services.ServicioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioServiceImpl implements ServicioService {
    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModalidadRepository modalidadRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public String CreateServicio(CreateServiceDto createServiceDto) throws NeoAdventuraException {
        Region region = regionRepository.findById(createServiceDto.getRegion_id())
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "RESTAURANT_NOT_FOUND"));

        Plataforma plataforma = plataformaRepository.findById(createServiceDto.getPlataforma_id())
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "RESTAURANT_NOT_FOUND"));

        Usuario usuario = usuarioRepository.findById(createServiceDto.getUsuario_id())
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "RESTAURANT_NOT_FOUND"));

        Modalidad modalidad = modalidadRepository.findById(createServiceDto.getModalidad_id())
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "RESTAURANT_NOT_FOUND"));

        Servicio servicio = new Servicio();

        System.out.println( "*************** create service: "+ createServiceDto.getPrice());

        servicio.setName(createServiceDto.getName());
        servicio.setDescription(createServiceDto.getDescription());
        servicio.setInit_valid_date(createServiceDto.getInit_valid_date());
        servicio.setEnd_valid_date(createServiceDto.getEnd_valid_date());
        servicio.setPrice(createServiceDto.getPrice());
        servicio.setModalidad(modalidad);
        servicio.setRegion(region);
        servicio.setPlataforma(plataforma);
        servicio.setUsuario(usuario);

        System.out.println( "*************** service: "+ servicio.getPrice());

        try {
            servicioRepository.save(servicio);
        } catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_ERROR", "INTERNAL_ERROR");
        }
        return servicio.getName();
    }

    @Override
    public ServicioDto getServicioById(Long id) throws NeoAdventuraException {
        return modelMapper.map(getServicioEntity(id), ServicioDto.class);
    }

    @Override
    public List<ServicioDto> getServicios() throws NeoAdventuraException {
        List<Servicio> serviciosEntity = servicioRepository.findAll();
        return serviciosEntity.stream().map(servicio -> modelMapper.map(servicio, ServicioDto.class))
                .collect(Collectors.toList());
    }

    private Servicio getServicioEntity(Long id) throws NeoAdventuraException {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOTFOUND-404", "SERVICIO_NOTFOUND-404"));
    }
}
