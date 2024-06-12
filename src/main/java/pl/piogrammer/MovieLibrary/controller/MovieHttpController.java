package pl.piogrammer.MovieLibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.piogrammer.MovieLibrary.MovieRepository;
import pl.piogrammer.MovieLibrary.model.Movie;
import pl.piogrammer.MovieLibrary.service.MovieService;

import java.util.List;

@Controller
public class MovieHttpController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    private final MovieService movieService;

    public MovieHttpController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/saveAddedMovie")
    public String saveAddedMovie(@ModelAttribute Movie movie, Model model) {
        if (movieService.saveMovieIfNotExists(movie)) {
            return "redirect:/httpmovies";
        } else {
            model.addAttribute("error", "Movie with the same name already exists.");
            return "error_added_movie";
        }
    }


    @GetMapping("/httpmovies")
    public String getAllHttpMovies(Model model){
        List<Movie> movie = movieRepository.getAll();
        model.addAttribute("movie",movie);
        return "movies";
    }

    @PostMapping("/update")
    public String updateMovie(@RequestParam("id") Long id, Model model) {
        // Fetch the movie by ID from the service or repository
        Movie movie = movieRepository.getById(id.intValue());
        model.addAttribute("movie", movie);
        return "update_movie_form"; // The name of your update form Thymeleaf template
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Integer id, Model model){

        Movie movie = movieRepository.getById(id.intValue());
        model.addAttribute("movie", movie);
        return "delete_movie_form";
    }
/*

    @PostMapping("/delete")
    public String delete(@RequestParam("id_movie") Integer id, Model model) {
        // Check if the movie exists before trying to delete it
        if (movieRepo.existsById(id)) {
            movieRepo.deleteById(id);
            model.addAttribute("message", "Movie deleted successfully");
        } else {
            model.addAttribute("message", "Movie not found");
        }
        return "delete_movie_confirmation";
    }*/


    @PostMapping ("/saveDeletedMovie")
    public String saveDeletedMovie(Movie movie){
        movieRepository.delete(movie);
        return "redirect:/httpmovies";
    }

    @PostMapping("/saveUpdate")
    public String saveUpdate(Movie movie) {
        // Save the updated movie details to the database
        movieRepository.updateMovie(movie);
        return "redirect:/httpmovies"; // R
        // edirect back to the movies list
    }
/*
    @PostMapping("/saveAddedMovie")
    public String saveAddedMovie(Movie movie){

        System.out.println(movieRepository.getAll());
        movieRepository.saveSingleMovie(movie);
        return "redirect:/httpmovies";
    }
*/

    @GetMapping("/addnewmovie")
    public String addNewMovie(Model model){
        model.addAttribute("movie", new Movie());
        return "add_movie_form";
    }



}
