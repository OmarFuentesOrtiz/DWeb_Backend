package com.neoadventura.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class PagoDto {
    private Long id;

    private Date pay_date;
    private BigDecimal mount;

    private Long metodo_id;
    private Long currency_id;
    private Long usuario_id;
}
