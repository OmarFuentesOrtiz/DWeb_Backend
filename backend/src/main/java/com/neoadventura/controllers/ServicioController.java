package com.neoadventura.controllers;

import com.neoadventura.dtos.CreateServiceDto;
import com.neoadventura.dtos.RegionDto;
import com.neoadventura.dtos.ServicioDto;
import com.neoadventura.exceptions.NeoAdventuraException;
import com.neoadventura.responses.NeoAdventuraResponse;
import com.neoadventura.services.RegionService;
import com.neoadventura.services.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/neo-adventura"+"/v1")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/servicios")
    public NeoAdventuraResponse<String> createServicio(@RequestBody CreateServiceDto createServiceDto)
            throws NeoAdventuraException{
        return new NeoAdventuraResponse<>("Success", String.valueOf(HttpStatus.OK),
                "OK", servicioService.CreateServicio(createServiceDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/servicios/{serviciosId}")
    public NeoAdventuraResponse<ServicioDto> getServicioById(@PathVariable Long servicioId)
            throws NeoAdventuraException {
        return new NeoAdventuraResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                servicioService.getServicioById(servicioId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/servicios")
    public NeoAdventuraResponse<List<ServicioDto>> getServicios()
            throws NeoAdventuraException{
        return new NeoAdventuraResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                servicioService.getServicios());
    }
}
