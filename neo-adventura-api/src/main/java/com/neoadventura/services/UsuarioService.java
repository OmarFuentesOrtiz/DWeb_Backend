package com.neoadventura.services;

public interface UsuarioService {

    List<UsuarioDto> getUsuario() throws NeoAdventuraException;
    UsuarioDto getUsuarioById(Long id) throws NeoAdventuraException;
}
