package com.neoadventura.services;

import com.neoadventura.dtos.CurrencyDto;
import com.neoadventura.exceptions.NeoAdventuraException;

import java.util.List;

public interface CurrencyService {
    CurrencyDto getCurrencyById(Long id) throws NeoAdventuraException;
    List<CurrencyDto> getCurrencys() throws NeoAdventuraException;
}
