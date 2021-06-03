package com.neoadventura.repositories;

import com.neoadventura.entities.Pago;
import com.neoadventura.entities.PagoServicio;
import com.neoadventura.entities.PagoServicioKey;
import com.neoadventura.entities.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagoServicioRepository extends JpaRepository<PagoServicio, PagoServicioKey> {

    Optional<PagoServicio> findByPagoAndServicio(Pago pago, Servicio servicio);

    List<PagoServicio> findAllByPago(Pago pago);
    List<PagoServicio> findAllByServicio(Servicio servicio);

}
