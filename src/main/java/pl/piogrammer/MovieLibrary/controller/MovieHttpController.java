package pl.piogrammer.MovieLibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.piogrammer.MovieLibrary.MovieRepository;
import pl.piogrammer.MovieLibrary.model.Movie;

import java.util.List;

@Controller
public class MovieHttpController {

    @Autowired
    MovieRepository movieRepository;



    @GetMapping("/httpmovies")
    public String getAllHttpMovies(Model model){
        List<Movie> movie = movieRepository.getAll();
        model.addAttribute("movie",movie);
        return "movies";
    }

    @PostMapping("/update")
    public String updateMovie(@RequestParam("id") Long id, Model model) {
        // Fetch the movie by ID from the service or repository
        Movie movie = movieRepository.getById(1);
        model.addAttribute("movie", movie);
        return "update_movie_form"; // The name of your update form Thymeleaf template
    }

    @PostMapping("/saveUpdate")
    public String saveUpdate(Movie movie) {
        // Save the updated movie details to the database
        movieRepository.saveSingleMovie(movie);
        return "redirect:/httpmovies"; // R



        // edirect back to the movies list
    }

}
