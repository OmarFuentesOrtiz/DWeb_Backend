package com.neoadventura.repositories;

import com.neoadventura.entities.Modalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModalidadRepository extends JpaRepository<Modalidad,Long> {


    Optional<Modalidad> findById(Long id);
    Optional<Modalidad> findModalidadById(Long id);

    @Query("select m from Modalidad m")
    List<Modalidad> findAll();
}
