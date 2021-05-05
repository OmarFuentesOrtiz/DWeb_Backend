package com.neoadventura.controllers;


import com.neoadventura.dtos.CreateUsuarioDto;
import com.neoadventura.dtos.UsuarioDto;
import com.neoadventura.exceptions.NeoAdventuraException;
import com.neoadventura.responses.NeoAdventuraResponse;
import com.neoadventura.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/neo-adventura"+"/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/usuarios")
    public NeoAdventuraResponse<UsuarioDto> createUsuario(@RequestBody CreateUsuarioDto createUsuarioDto)
            throws NeoAdventuraException {
        return new NeoAdventuraResponse<>("Success", String.valueOf(HttpStatus.OK),
                "OK", usuarioService.CreateUsuario(createUsuarioDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios/{usuariosId}")
    public NeoAdventuraResponse<UsuarioDto> getUsuarioById(@PathVariable Long servicioId)
            throws NeoAdventuraException {
        return new NeoAdventuraResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                usuarioService.getUsuarioById(servicioId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios")
    public NeoAdventuraResponse<List<UsuarioDto>> getUsuario()
            throws NeoAdventuraException{
        return new NeoAdventuraResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                usuarioService.getUsuarios());
    }
}
