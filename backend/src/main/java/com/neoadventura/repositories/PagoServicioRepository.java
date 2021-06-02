package com.neoadventura.repositories;

import com.neoadventura.entities.Pago;
import com.neoadventura.entities.PagoServicio;
import com.neoadventura.entities.PagoServicioKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagoServicioRepository extends JpaRepository<PagoServicio, PagoServicioKey> {

}
