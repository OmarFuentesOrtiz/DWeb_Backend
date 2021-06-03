package com.neoadventura.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagoServicioDto {
    private Long pago_id;
    private Long servicio_id;

    private Boolean reported;
    private String description;
}
