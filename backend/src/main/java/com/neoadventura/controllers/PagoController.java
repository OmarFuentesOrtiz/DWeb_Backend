package com.neoadventura.controllers;

import com.neoadventura.dtos.PagoDto;
import com.neoadventura.exceptions.NeoAdventuraException;
import com.neoadventura.responses.NeoAdventuraResponse;
import com.neoadventura.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/neo-adventura"+"/v1")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/pagos")
    public NeoAdventuraResponse<PagoDto> createPago(@RequestBody PagoDto pagoDto)
            throws NeoAdventuraException {
        return new NeoAdventuraResponse<>("Success", String.valueOf(HttpStatus.OK),
                "OK", pagoService.CreatePago(pagoDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/pagos/{pagoId}")
    public NeoAdventuraResponse<PagoDto> getPagoById(@PathVariable Long pagoId)
            throws NeoAdventuraException {
        return new NeoAdventuraResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                pagoService.getPagoById(pagoId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/pagos")
    public NeoAdventuraResponse<List<PagoDto>> getPagos()
            throws NeoAdventuraException{
        return new NeoAdventuraResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                pagoService.getPagos());
    }

}
