package com.neoadventura.dtos;

import lombok.Getter;

import java.util.Date;

@Getter
public class CreateServiceDto {
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
