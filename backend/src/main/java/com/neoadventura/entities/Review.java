package com.neoadventura.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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
    @JoinColumn(
            name="usuario_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "usuario_review_fk"
            )
    )
    private Usuario usuario;

    @ManyToOne
    @MapsId("servicioId")
    @JoinColumn(
            name="servicio_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "servicio_review_fk"
            )
    )
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Review that = (Review) o;
        return Objects.equals(servicio, that.servicio) &&
                Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(servicio, usuario);
    }

}

