package com.example.moviecatalogue.service;
import com.example.moviecatalogue.model.Favorite;
import org.springframework.stereotype.Service;
import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.model.TmdbResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Movie> getTrendingMovies() {
        String url = apiUrl + "/trending/movie/week?api_key=" + apiKey;
        TmdbResponse response = restTemplate.getForObject(url, TmdbResponse.class);
        return response != null ? response.getResults() : List.of();
    }
    public List<Movie> searchMovies(String query) {
        String url = apiUrl + "/search/movie?api_key=" + apiKey + "&query=" + query;
        TmdbResponse response = restTemplate.getForObject(url, TmdbResponse.class);
        return response != null ? response.getResults() : List.of();
    }
    public Movie getMovieDetails(String movieId) throws JSONException {
        String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        JSONObject movieJson = new JSONObject(response.getBody());
        Movie movie = new Movie();
        movie.setId(movieJson.getInt("id"));
        movie.setTitle(movieJson.getString("title"));
        movie.setOverview(movieJson.getString("overview"));
        movie.setPosterPath(movieJson.getString("poster_path"));
        movie.setReleaseDate(movieJson.getString("release_date"));
        movie.setRating(movieJson.getDouble("vote_average"));

        return movie;
    }
    private List<Favorite> favorites = new ArrayList<>();

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void addToFavorites(Favorite favorite) {
        favorites.add(favorite);
    }

}
