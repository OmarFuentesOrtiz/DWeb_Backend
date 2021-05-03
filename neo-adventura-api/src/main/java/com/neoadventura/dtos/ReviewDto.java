package com.neoadventura.dtos;

import com.neoadventura.entities.ReviewKey;
import com.neoadventura.entities.Servicio;
import com.neoadventura.entities.Usuario;
import lombok.Getter;

import javax.persistence.*;

@Getter
public class ReviewDto {

    //private Long reviewKey_id;
    private Long usuario_id;
    private Long servicio_id;

    private Integer score;
    private String description;
    private Boolean reported;

}
