package com.neoadventura.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ServicioDto {
    private Long id;
    private String name;
    private String description;
    private Date init_valid_date;
    private Date end_valid_date;
    private Double price;
    private Long modalidad_id;
    private Long region_id;
    private Long plataforma_id;
    private Long usuario_id;
}
