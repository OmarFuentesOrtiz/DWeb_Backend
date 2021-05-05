package com.neoadventura.services.impl;

import com.neoadventura.dtos.ModalidadDto;
import com.neoadventura.dtos.PaisDto;
import com.neoadventura.dtos.PlataformaDto;
import com.neoadventura.entities.Plataforma;
import com.neoadventura.exceptions.NeoAdventuraException;
import com.neoadventura.exceptions.NotFoundException;
import com.neoadventura.repositories.PlataformaRepository;
import com.neoadventura.services.PlataformaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlataformaServiceImpl implements PlataformaService {

    @Autowired
    private PlataformaRepository plataformaRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public PlataformaDto getPlataformaById(Long id) throws NeoAdventuraException {
        return modelMapper.map(getPlataformaEntity(id), PlataformaDto.class);
    }

    @Override
    public List<PlataformaDto> getPlataformas() throws NeoAdventuraException {
        List<Plataforma> plataformasEntity = plataformaRepository.findAll();
        return plataformasEntity.stream().map(plataforma -> modelMapper.map(plataforma, PlataformaDto.class))
                .collect(Collectors.toList());
    }

    private Plataforma getPlataformaEntity(Long id) throws NeoAdventuraException{
        return plataformaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOTFOUND-404", "PLATAFORMA_NOTFOUND-404"));
    }
}
