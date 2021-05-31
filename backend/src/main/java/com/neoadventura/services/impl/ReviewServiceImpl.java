package com.neoadventura.services.impl;

import com.neoadventura.dtos.CreateReviewDto;
import com.neoadventura.dtos.ReviewDto;
import com.neoadventura.entities.*;
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
        List<ReviewDto> reviewDtos = reviewsEntity.stream().map(x->modelmapper.map(x,ReviewDto.class))
                .collect(Collectors.toList());
        for (int i=0; i<reviewDtos.size();++i) {
            reviewDtos.get(i).setServicio_id(reviewsEntity.get(i).getId().getServicioId());
            reviewDtos.get(i).setUsuario_id(reviewsEntity.get(i).getId().getUsuarioId());
        }
        return reviewDtos;
    }

    @Transactional
    @Override
    public ReviewDto CreateReview(CreateReviewDto createReviewDto) throws NeoAdventuraException {
        Servicio servicio = getServicioEntity(createReviewDto.getServicio_id());

        Usuario usuario = getUsuarioEntity(createReviewDto.getUsuario_id());


        Review review=new Review(
                new ReviewKey(
                        createReviewDto.getServicio_id(),
                        createReviewDto.getUsuario_id()
                ),
                usuario,
                servicio,
                createReviewDto.getScore(),
                createReviewDto.getDescription(),
                createReviewDto.getReported()
        );

        try {
            review=reviewRepository.save(review);
        }catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return modelmapper.map(getReviewEntity(review.getUsuario().getId(), review.getServicio().getId()),ReviewDto.class);
    }

    private Usuario getUsuarioEntity(Long id) throws NeoAdventuraException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOTFOUND-404", "USUARIO_NOTFOUND-404"));
    }

    private Servicio getServicioEntity(Long id) throws NeoAdventuraException {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOTFOUND-404", "SERVICIO_NOTFOUND-404"));
    }

    private Review getReviewEntity(Long usuarioId, Long servicioId) throws NeoAdventuraException{
        return reviewRepository.findByUsuarioAndServicio(getUsuarioEntity(usuarioId), getServicioEntity(servicioId))
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","REVIEW_NOTFOUND-404"));
    }
}
