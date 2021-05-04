package com.neoadventura.services.impl;
import com.neoadventura.dtos.UsuarioDto;
import com.neoadventura.entities.Usuario;
import com.neoadventura.exceptions.NeoAdventuraException;
import com.neoadventura.exceptions.NotFoundException;
import com.neoadventura.repositories.UsuarioRepository;
import com.neoadventura.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
public class UsuarioServiceImpl {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UsuarioDto getUsuarioById(Long UsuarioId) throws NeoAdventuraException {
        return modelMapper.map(getUsuarioEntity(UsuarioId), UsuarioDto.class);
    }

    @Override
    public List<Usuario> getUsuario() throws NeoAdventuraException {
        List<Usuario> ServicioEntity = usuarioRepository.findAll();
        return ServicioEntity.stream().map(Servicio -> modelMapper.map(Servicio, UsuarioDto.class))
                .collect(Collectors.toList());
    }

    private Usuario getUsuarioEntity(Long Usuario) throws NeoAdventuraException {
        return usuarioRepository.findById(Usuario)
                .orElseThrow(() -> new NotFoundException("ERROR NOT FOUND ID", "NOT FOUND USER ID"));
    }
}
