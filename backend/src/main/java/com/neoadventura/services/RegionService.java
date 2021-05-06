package com.neoadventura.services;

import com.neoadventura.dtos.RegionDto;
import com.neoadventura.exceptions.NeoAdventuraException;

import java.util.List;

public interface RegionService {
    RegionDto getRegionById(Long id) throws NeoAdventuraException;
    List<RegionDto> getRegions() throws NeoAdventuraException;
}
