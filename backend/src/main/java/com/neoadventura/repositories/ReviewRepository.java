package com.neoadventura.repositories;

import com.neoadventura.entities.Review;
import com.neoadventura.entities.ReviewKey;
import com.neoadventura.entities.Servicio;
import com.neoadventura.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewKey> {

    Optional<Review> findById(ReviewKey id);
    Optional<Review> findByUsuarioAndServicio(Usuario usuario, Servicio servicio);

//    @Modifying
//    @Transactional
//    Optional<Review> deleteByUsuario(String usuario);

    @Query("select r from Review r")
    List<Review> findReviews();

    List<Review> findAllByServicio(Servicio servicio);
    List<Review> findAllByUsuario(Usuario usuario);
}