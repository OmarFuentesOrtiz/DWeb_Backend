package com.neoadventura.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name="paises",
        uniqueConstraints = {
                @UniqueConstraint(name="pais_name_unique",
                        columnNames = "name")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pais {
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


    @OneToMany(
            mappedBy = "pais",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Region> regiones=new ArrayList<>();
}
