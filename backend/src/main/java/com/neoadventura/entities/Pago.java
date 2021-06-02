package com.neoadventura.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name="pagos"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pago {
    @Id
    @SequenceGenerator(
            name="pago_sequence",
            sequenceName = "pago_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pago_sequence"
    )
    @Column(
            name="id",
            updatable = false
    )
    private Long id;


    @ManyToOne
    @JoinColumn(
            name="metodo_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "metodo_pago_id"
            )
    )
    private Metodo metodo;

    @ManyToOne
    @JoinColumn(
            name="currency_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "currency_pago_id"
            )
    )
    private Currency currency;

    @ManyToOne
    @JoinColumn(
            name="usuario_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "usuario_pago_id"
            )
    )
    private Usuario usuario;


    @OneToMany(
            mappedBy = "pago",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    private List<PagoServicio> pagoServicios = new ArrayList<>();
}
