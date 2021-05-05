package com.neoadventura.repositories;

import com.neoadventura.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    @Query(
            value="select now()",
            nativeQuery = true
    )
    Date getNow();

}
