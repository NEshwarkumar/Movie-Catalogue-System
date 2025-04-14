package com.example.moviecatalogue.repository;

import com.example.moviecatalogue.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    Optional<Favorite> findByMovieId(Integer movieId);
}
