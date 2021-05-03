package com.neoadventura.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name="reviews"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @EmbeddedId
    private ReviewKey id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("servicioId")
    @JoinColumn(name="servicio_id")
    private Servicio servicio;

    @Column(
            name="score",
            nullable = false,
            columnDefinition = "SMALLINT"
    )
    private Integer score;

    @Column(
            name="description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name="reported",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private Boolean reported;
}

