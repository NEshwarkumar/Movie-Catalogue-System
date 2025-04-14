package com.example.moviecatalogue.controller;

import com.example.moviecatalogue.model.Favorite;
import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String getTrendingMovies(@RequestParam(required = false) String search, Model model) {
        List<Movie> movies;
        if (search != null && !search.isEmpty()) {
            movies = movieService.searchMovies(search);
            model.addAttribute("searchQuery", search);
        } else {
            movies = movieService.getTrendingMovies();
            model.addAttribute("searchQuery", null);
        }
        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("/search")
    public String searchMovies(@RequestParam("query") String query, Model model) {
        List<Movie> movies = movieService.searchMovies(query);
        model.addAttribute("movies", movies);
        model.addAttribute("searchQuery", query);
        return "index";
    }
    @GetMapping("/movie/{id}")
    public String movieDetails(@PathVariable String id, Model model) {
        Movie movie = movieService.getMovieDetails(id);
        model.addAttribute("movie", movie);
        return "movie-details";
    }
}
