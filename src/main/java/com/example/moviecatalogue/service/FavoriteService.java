package com.example.moviecatalogue.service;

import com.example.moviecatalogue.model.Favorite;
import com.example.moviecatalogue.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public void addFavorite(Favorite favorite) {
        favoriteRepository.save(favorite);
    }

    public void removeFavorite(Integer id) {
        favoriteRepository.deleteById(id);
    }

    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    public Optional<Favorite> findByMovieId(Integer movieId) {
        return favoriteRepository.findByMovieId(movieId);
    }
}
