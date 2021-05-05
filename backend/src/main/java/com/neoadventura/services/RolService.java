package com.neoadventura.services;

import com.neoadventura.dtos.RolDto;
import com.neoadventura.exceptions.NeoAdventuraException;

import java.util.List;

public interface RolService {
    RolDto getRolById(Long id) throws NeoAdventuraException;
    List<RolDto> getRoles() throws NeoAdventuraException;
}
