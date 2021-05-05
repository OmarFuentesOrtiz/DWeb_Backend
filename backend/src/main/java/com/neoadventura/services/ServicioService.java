package com.neoadventura.services;

import com.neoadventura.dtos.CreateServiceDto;
import com.neoadventura.dtos.PlataformaDto;
import com.neoadventura.dtos.ServicioDto;
import com.neoadventura.exceptions.NeoAdventuraException;

import java.util.List;

public interface ServicioService {
    String CreateServicio(CreateServiceDto createServiceDto) throws NeoAdventuraException;
    ServicioDto getServicioById(Long id) throws NeoAdventuraException;
    List<ServicioDto> getServicios() throws NeoAdventuraException;
}
