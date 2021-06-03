package com.neoadventura.services.impl;

import com.neoadventura.dtos.PagoDto;
import com.neoadventura.entities.Currency;
import com.neoadventura.entities.Metodo;
import com.neoadventura.entities.Pago;
import com.neoadventura.entities.Usuario;
import com.neoadventura.exceptions.InternalServerErrorException;
import com.neoadventura.exceptions.NeoAdventuraException;
import com.neoadventura.exceptions.NotFoundException;
import com.neoadventura.repositories.CurrencyRepository;
import com.neoadventura.repositories.MetodoRepository;
import com.neoadventura.repositories.PagoRepository;
import com.neoadventura.repositories.UsuarioRepository;
import com.neoadventura.services.PagoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private MetodoRepository metodoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final ModelMapper modelMapper = new ModelMapper();


    @Override
    public PagoDto CreatePago(PagoDto pagoDto) throws NeoAdventuraException {
        Currency currency = currencyRepository.findById(pagoDto.getCurrency_id())
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "CURRENCY_NOT_FOUND"));

        Metodo metodo = metodoRepository.findById(pagoDto.getMetodo_id())
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "METODO_NOT_FOUND"));

        Usuario usuario = usuarioRepository.findById(pagoDto.getUsuario_id())
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "USUARIO_NOT_FOUND"));


        Pago pago = new Pago();


        pago.setCurrency(currency);
        pago.setMetodo(metodo);
        pago.setUsuario(usuario);
        pago.setPay_date(pagoDto.getPay_date());
        pago.setMount(pagoDto.getMount());


        try {
            pagoRepository.save(pago);
        } catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_ERROR", "INTERNAL_ERROR");
        }
        return modelMapper.map(getPagoEntity(pago.getId()), PagoDto.class);
    }

    @Override
    public PagoDto getPagoById(Long id) throws NeoAdventuraException {
        Pago pago =getPagoEntity(id);
        PagoDto pagoDto = modelMapper.map(getPagoEntity(id), PagoDto.class);
        pagoDto.setCurrency_id(pago.getCurrency().getId());
        pagoDto.setMetodo_id(pago.getMetodo().getId());
        pagoDto.setUsuario_id(pago.getUsuario().getId());
        return pagoDto;
    }

    @Override
    public List<PagoDto> getPagos() throws NeoAdventuraException {
        List<Pago> pagosEntity = pagoRepository.findAll();
        List<PagoDto> pagoDtos = pagosEntity.stream().map(servicio -> modelMapper.map(servicio, PagoDto.class))
                .collect(Collectors.toList());
        for (int i = 0; i < pagoDtos.size(); i++) {
            pagoDtos.get(i).setCurrency_id(pagosEntity.get(i).getCurrency().getId());
            pagoDtos.get(i).setMetodo_id(pagosEntity.get(i).getMetodo().getId());
            pagoDtos.get(i).setUsuario_id(pagosEntity.get(i).getUsuario().getId());
        }
        return pagoDtos;
    }

    private Pago getPagoEntity(Long id) throws NeoAdventuraException {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOTFOUND-404", "PAGO_NOTFOUND-404"));
    }

}
