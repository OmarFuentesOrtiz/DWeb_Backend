package com.neoadventura.services.impl;

import com.neoadventura.dtos.CreateUsuarioDto;
import com.neoadventura.dtos.ServicioDto;
import com.neoadventura.dtos.UsuarioDto;
import com.neoadventura.entities.*;
import com.neoadventura.exceptions.InternalServerErrorException;
import com.neoadventura.exceptions.NeoAdventuraException;
import com.neoadventura.exceptions.NotFoundException;
import com.neoadventura.repositories.*;
import com.neoadventura.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public UsuarioDto CreateUsuario(CreateUsuarioDto createUsuarioDto) throws NeoAdventuraException {

        //System.out.println(createUsuarioDto.getRol_id());
        Rol rol = rolRepository.findById(createUsuarioDto.getRol_id())
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "ROL_IN_USER_NOT_FOUND"));

        Usuario usuario = new Usuario();

        //System.out.println(rol.getId());
        //System.out.println( "*************** create service: "+ createUsuarioDto.getPrice());

        usuario.setName(createUsuarioDto.getName()); //
        usuario.setEmail(createUsuarioDto.getEmail()); //
        usuario.setBirth_day(createUsuarioDto.getBirth_day()); //
        usuario.setRegistered(usuarioRepository.getNow());
        usuario.setSuscribed(false);
        usuario.setMonedero_virtual(createUsuarioDto.getMonedero_oferta()); //corregir más adelante
        usuario.setMonedero_oferta(createUsuarioDto.getMonedero_oferta()); //
        usuario.setSame_language(false);
        usuario.setBanned(false);
        usuario.setRol(rol);

        //System.out.println( "*************** service: "+ usuario.getPrice());

        try {
            usuarioRepository.save(usuario);
        } catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_ERROR", "INTERNAL_ERROR");
        }
        return modelMapper.map(getUsuarioEntity(usuario.getId()),UsuarioDto.class);
    }

    @Override
    public UsuarioDto getUsuarioById(Long id) throws NeoAdventuraException {
        return modelMapper.map(getUsuarioEntity(id), UsuarioDto.class);
    }

    @Override
    public List<UsuarioDto> getUsuarios() throws NeoAdventuraException {
        List<Usuario> serviciosEntity = usuarioRepository.findAll();
        return serviciosEntity.stream().map(usuario -> modelMapper.map(usuario, UsuarioDto.class))
                .collect(Collectors.toList());
    }

    private Usuario getUsuarioEntity(Long id) throws NeoAdventuraException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOTFOUND-404", "USUARIO_NOTFOUND-404"));
    }

}
