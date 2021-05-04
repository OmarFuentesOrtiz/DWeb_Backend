package com.neoadventura.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UsuarioDto {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private Date birth_day;
    private Date registered;
    private Boolean suscribed;
    private Double monedero_virtual;
    private Double monedero_oferta;
    private Boolean same_language;
    private Boolean banned;
    private Long rol_id;
}
