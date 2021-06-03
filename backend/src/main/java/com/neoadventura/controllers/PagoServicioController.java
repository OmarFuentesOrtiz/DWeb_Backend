package com.neoadventura.controllers;

import com.neoadventura.dtos.PagoServicioDto;
import com.neoadventura.exceptions.NeoAdventuraException;
import com.neoadventura.responses.NeoAdventuraResponse;
import com.neoadventura.services.PagoServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= "/neo-adventura"+"/v1")
public class PagoServicioController {

    @Autowired
    private PagoServicioService pagoServicioService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/pago_servicios")
    public NeoAdventuraResponse<PagoServicioDto> createPago(@RequestBody PagoServicioDto pagoServicioDto)
            throws NeoAdventuraException {
        return new NeoAdventuraResponse<>("Success", String.valueOf(HttpStatus.OK),
                "OK", pagoServicioService.CreatePagoServicio(pagoServicioDto));
    }

}
