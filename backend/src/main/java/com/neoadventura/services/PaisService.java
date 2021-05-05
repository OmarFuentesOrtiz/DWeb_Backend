package com.neoadventura.services;

import com.neoadventura.dtos.ModalidadDto;
import com.neoadventura.dtos.PaisDto;
import com.neoadventura.exceptions.NeoAdventuraException;

import java.util.List;

public interface PaisService {
    PaisDto getPaisById(Long id) throws NeoAdventuraException;
    List<PaisDto> getPaises() throws NeoAdventuraException;
}
