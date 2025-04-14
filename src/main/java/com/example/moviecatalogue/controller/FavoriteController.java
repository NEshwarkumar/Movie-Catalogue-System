package com.example.moviecatalogue.controller;
import com.example.moviecatalogue.model.Favorite;
import com.example.moviecatalogue.repository.FavoriteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;

    public FavoriteController(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @PostMapping("/add")
    public String addFavorite(@RequestParam("movieId") int movieId,
                              @RequestParam("movieTitle") String movieTitle,
                              @RequestParam("moviePoster") String moviePoster,
                              Model model) {
        // Check if already favorited
        if (favoriteRepository.findByMovieId(movieId).isPresent()) {
            model.addAttribute("error", "Movie is already in favorites!");
            return "redirect:/favorites"; // Optionally pass a message
        }

        Favorite favorite = new Favorite();
        favorite.setMovieId(movieId);
        favorite.setMovieTitle(movieTitle);
        favorite.setMoviePoster(moviePoster);
        favoriteRepository.save(favorite);

        return "redirect:/favorites";
    }
    @GetMapping
    public String getFavorites(Model model) {
        List<Favorite> favorites = favoriteRepository.findAll();
        model.addAttribute("favorites", favorites);
        return "favorites"; // Display favorites page
    }


    @PostMapping("/remove")
    public String removeFavorite(@RequestParam("id") int id) {
        favoriteRepository.deleteById(id);
        return "redirect:/favorites"; // Redirect back to favorites after removal
    }
}
