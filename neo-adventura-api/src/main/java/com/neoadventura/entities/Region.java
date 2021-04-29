package com.neoadventura.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name="regiones"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    @Id
    @SequenceGenerator(
            name="plataforma_sequence",
            sequenceName = "plataforma_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "plataforma_sequence"
    )
    @Column(
            name="id",
            updatable = false
    )
    private Long id;

    @Column(
            name="name",
            nullable = false,
            columnDefinition = "VARCHAR(20)"
    )
    private String name;

    @ManyToOne
    @JoinColumn(
            name="pais_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="pais_region_fk"
            )
    )
    private Pais pais;
}
