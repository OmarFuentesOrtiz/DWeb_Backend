package com.neoadventura.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(
        name="modalidades"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Modalidad {
    @Id
    @SequenceGenerator(
            name="modalidad_sequence",
            sequenceName = "modalidad_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "modalidad_sequence"
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


    @OneToMany(
            mappedBy = "modalidad",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Servicio> servicios = new ArrayList<>();

}

