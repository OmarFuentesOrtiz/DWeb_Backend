package com.neoadventura.services;

import com.neoadventura.dtos.PaisDto;
import com.neoadventura.dtos.PlataformaDto;
import com.neoadventura.exceptions.NeoAdventuraException;

import java.util.List;

public interface PlataformaService {
    PlataformaDto getPlataformaById(Long id) throws NeoAdventuraException;
    List<PlataformaDto> getPlataformas() throws NeoAdventuraException;
}
