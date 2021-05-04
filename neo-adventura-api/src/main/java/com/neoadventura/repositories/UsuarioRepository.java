package com.neoadventura.repositories;

import com.neoadventura.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findById(Long id);
    Optional<Usuario> findUsuarioById(Long id);

    @Query("select from Usuario")
    List<Usuario> findAll();
}
