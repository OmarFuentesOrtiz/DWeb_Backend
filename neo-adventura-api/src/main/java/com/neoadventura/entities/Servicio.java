package com.neoadventura.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(
        name="servicios"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servicio {
    @Id
    @SequenceGenerator(
            name="pais_sequence",
            sequenceName = "pais_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pais_sequence"
    )
    @Column(
            name="id",
            updatable = false
    )
    private Long id;

    @Column(
            name="name",
            nullable = false,
            columnDefinition = "VARCHAR(15)"
    )
    private String name;

    @Column(
            name="description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name="init_valid_date",
            nullable = false,
            columnDefinition = "DATE"
    )
    private Date init_valid_date;

    @Column(
            name="end_valid_date",
            nullable = false,
            columnDefinition = "DATE"
    )
    private Date end_valid_date;

    @Column(
            name="price",
            nullable = false,
            columnDefinition = "MONEY"
    )
    private Double price;

    @ManyToOne
    @JoinColumn(
            name="modalidad_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="modalidad_servicio_fk"
            )
    )
    private Modalidad modalidad;

    @ManyToOne
    @JoinColumn(
            name="region_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="region_servicio_fk"
            )
    )
    private Region region;

    @ManyToOne
    @JoinColumn(
            name="plataforma_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="plataforma_servicio_fk"
            )
    )
    private Plataforma plataforma;

    @ManyToOne
    @JoinColumn(
            name="usuario_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="usuario_servicio_fk"
            )
    )
    private Usuario usuario;

    @OneToMany(
            mappedBy = "servicio",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Review> reviews = new ArrayList<>();
}
