package com.neoadventura.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PagoServicioKey implements Serializable {
    @Column(name= "pago_id")
    private Long pagoId;

    @Column(name= "servicio_id")
    private Long servicioId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PagoServicioKey that = (PagoServicioKey) o;
        return Objects.equals(pagoId, that.pagoId) &&
                Objects.equals(servicioId, that.servicioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pagoId, servicioId);
    }
}
