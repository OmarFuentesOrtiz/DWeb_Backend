package com.neoadventura.services;

import com.neoadventura.dtos.PagoDto;
import com.neoadventura.exceptions.NeoAdventuraException;

import java.util.List;

public interface PagoService {

    PagoDto getPagoById(Long id) throws NeoAdventuraException;
    List<PagoDto> getPagos() throws NeoAdventuraException;
    PagoDto CreatePago(PagoDto pagoDto) throws NeoAdventuraException;
}
