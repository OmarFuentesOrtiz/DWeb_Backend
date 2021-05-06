package com.neoadventura.services.impl;

import com.neoadventura.dtos.CreateServiceDto;
import com.neoadventura.dtos.ModalidadDto;
import com.neoadventura.dtos.ServicioDto;
import com.neoadventura.dtos.UsuarioDto;
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
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "REGION_NOT_FOUND"));

        Plataforma plataforma = plataformaRepository.findById(createServiceDto.getPlataforma_id())
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "PLATAFORMA_NOT_FOUND"));

        Usuario usuario = usuarioRepository.findById(createServiceDto.getUsuario_id())
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "USUARIO_NOT_FOUND"));

        Modalidad modalidad = modalidadRepository.findById(createServiceDto.getModalidad_id())
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "MODALIDA_NOT_FOUND"));

        Servicio servicio = new Servicio();


        servicio.setName(createServiceDto.getName());
        servicio.setDescription(createServiceDto.getDescription());
        servicio.setInit_valid_date(createServiceDto.getInit_valid_date());
        servicio.setEnd_valid_date(createServiceDto.getEnd_valid_date());
        servicio.setPrice(createServiceDto.getPrice());
        servicio.setModalidad(modalidad);
        servicio.setRegion(region);
        servicio.setPlataforma(plataforma);
        servicio.setUsuario(usuario);


        try {
            servicioRepository.save(servicio);
        } catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_ERROR", "INTERNAL_ERROR");
        }
        return servicio.getName();
    }

    @Override
    public ServicioDto getServicioById(Long id) throws NeoAdventuraException {
        Servicio servicio =getServicioEntity(id);
        ServicioDto servicioDto = modelMapper.map(getServicioEntity(id), ServicioDto.class);
        servicioDto.setModalidad_id(servicio.getModalidad().getId());
        servicioDto.setPlataforma_id(servicio.getPlataforma().getId());
        servicioDto.setRegion_id(servicio.getRegion().getId());
        servicioDto.setUsuario_id(servicio.getUsuario().getId());
        return servicioDto;
    }

    @Override
    public List<ServicioDto> getServicios() throws NeoAdventuraException {
        List<Servicio> serviciosEntity = servicioRepository.findAll();
        List<ServicioDto> servicioDtos = serviciosEntity.stream().map(servicio -> modelMapper.map(servicio, ServicioDto.class))
                .collect(Collectors.toList());
        for (int i = 0; i < servicioDtos.size(); i++) {
            servicioDtos.get(i).setModalidad_id(serviciosEntity.get(i).getModalidad().getId());
            servicioDtos.get(i).setPlataforma_id(serviciosEntity.get(i).getPlataforma().getId());
            servicioDtos.get(i).setRegion_id(serviciosEntity.get(i).getRegion().getId());
            servicioDtos.get(i).setUsuario_id(serviciosEntity.get(i).getUsuario().getId());
        }
        return servicioDtos;
    }

    private Servicio getServicioEntity(Long id) throws NeoAdventuraException {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOTFOUND-404", "SERVICIO_NOTFOUND-404"));
    }
}
