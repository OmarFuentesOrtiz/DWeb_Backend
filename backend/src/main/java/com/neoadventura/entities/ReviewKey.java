package com.neoadventura.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReviewKey implements Serializable {
    @Column(name= "servicio_id")
    private Long servicioId;

    @Column(name= "usuario_id")
    private Long usuarioId;

    // hashcode and equals implementation
}
