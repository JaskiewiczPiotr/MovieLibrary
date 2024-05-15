package pl.piogrammer.MovieLibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
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
}
