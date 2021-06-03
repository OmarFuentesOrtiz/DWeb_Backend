package com.neoadventura.services;

import com.neoadventura.dtos.PagoServicioDto;
import com.neoadventura.exceptions.NeoAdventuraException;

public interface PagoServicioService {

    PagoServicioDto CreatePagoServicio(PagoServicioDto pagoServicioDto) throws NeoAdventuraException;

}
