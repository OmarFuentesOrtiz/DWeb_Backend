package com.neoadventura.services;

import com.neoadventura.dtos.ModalidadDto;
import com.neoadventura.exceptions.NeoAdventuraException;

import java.util.List;

public interface ModalidadService {
    List<ModalidadDto> getModalidades() throws NeoAdventuraException;
    ModalidadDto getModalidadById(Long id) throws NeoAdventuraException;
}
