package com.neoadventura.services.impl;

import com.neoadventura.dtos.CreateReviewDto;
import com.neoadventura.dtos.ReviewDto;
import com.neoadventura.entities.Plataforma;
import com.neoadventura.entities.Review;
import com.neoadventura.entities.Servicio;
import com.neoadventura.entities.Usuario;
import com.neoadventura.exceptions.InternalServerErrorException;
import com.neoadventura.exceptions.NeoAdventuraException;
import com.neoadventura.exceptions.NotFoundException;
import com.neoadventura.repositories.*;
import com.neoadventura.services.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    private static final ModelMapper modelmapper= new ModelMapper();

    @Override
    public List<ReviewDto> getReviews() throws NeoAdventuraException {
        List<Review> reviewsEntity=reviewRepository.findAll();
        return reviewsEntity.stream().map(review->modelmapper.map(review,ReviewDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ReviewDto CreateReview(CreateReviewDto createReviewDto) throws NeoAdventuraException {
        Servicio servicio = servicioRepository.findById(createReviewDto.getServicio_id())
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "SERVICIO_NOT_FOUND"));

        Usuario usuario = usuarioRepository.findById(createReviewDto.getUsuario_id())
                .orElseThrow(() -> new NotFoundException("NOT-401-1", "USUARIO_NOT_FOUND"));


        Review review=new Review();
        review.setUsuario(usuario);
        review.setServicio(servicio);

        review.setScore(createReviewDto.getScore());
        review.setDescription(createReviewDto.getDescription());
        review.setReported(createReviewDto.getReported());

        try {
            System.out.println("Me voy a guardar");
            review=reviewRepository.save(review);

        }catch (Exception ex){
            System.out.println("Falle en el intento");
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR");
        }
        return modelmapper.map(getReviewEntity(review.getUsuario().getId(), review.getServicio().getId()),ReviewDto.class);
    }

    private Review getReviewEntity(Long usuarioId, Long servicioId) throws NeoAdventuraException{
        return reviewRepository.findByUsuarioAndServicio(usuarioId, servicioId)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","RESTAURANT_NOTFOUND-404"));
    }
}
