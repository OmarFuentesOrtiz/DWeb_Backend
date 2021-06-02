package com.neoadventura.services;

import com.neoadventura.dtos.MetodoDto;
import com.neoadventura.exceptions.NeoAdventuraException;

import java.util.List;

public interface MetodoService {
    MetodoDto getMetodoById(Long id) throws NeoAdventuraException;
    List<MetodoDto> getMetodos() throws NeoAdventuraException;
}
