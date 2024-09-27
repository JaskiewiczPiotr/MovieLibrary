package pl.piogrammer.MovieLibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.piogrammer.MovieLibrary.Exceptions.MovieNotFoundException;
import pl.piogrammer.MovieLibrary.MovieRepository;
import pl.piogrammer.MovieLibrary.model.FavoriteMovie;
import pl.piogrammer.MovieLibrary.model.Movie;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

@Controller
public class MovieHttpController {

    @Autowired
    MovieRepository movieRepository;

    FavoriteMovie favoriteMovie;

    @GetMapping("/httpmovies")
    public String getAllHttpMovies(Model model){
        List<Movie> movie = movieRepository.getAll();
        model.addAttribute("movie",movie);
        return "movies";
    }

    @PostMapping("/update")
    public String updateMovie(@RequestParam("id") int id, Model model) {
        // Fetch the movie by ID from the service or repository
        Movie movie = movieRepository.getById(id);
        model.addAttribute("movie", movie);
        return "update_movie_form"; // The name of your update form Thymeleaf template
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model){
        Movie movie = movieRepository.getById(id.byteValue());
        model.addAttribute("movie", movie);
        return "delete_movie_form";
    }
/*
    @PostMapping("/addtofavorite")
    public String addToFavorite(@RequestParam("id") int id, Model model){
        Movie movie = movieRepository.getById(id);
        model.addAttribute("movie", movie);
        return "update_movie_form";

    }
*/
/*
    @GetMapping("/addnewmovie")
    public String addNewMovie(Model model){
        model.addAttribute("movie", new Movie());
        return "add_movie_form";
    }*/

    @PostMapping ("/saveDeletedMovie")
    public String saveDeletedMovie(Movie movie){
        movieRepository.delete(movie);
        return "redirect:/httpmovies";
    }




/*
    @PostMapping("/saveUpdate")
    public String saveUpdate(Movie movie) {
        // Save the updated movie details to the database
        movieRepository.updateMovie(movie);
        return "redirect:/httpmovies"; // R
        // edirect back to the movies list
    }*/

    @PostMapping("/saveUpdate")
    public String saveUpdate(@RequestParam("id_movie") int idMovie,
                             @RequestParam("movie_name") String movieName,
                             @RequestParam("rating") int rating,
                             @RequestParam("image") MultipartFile imageFile,
                             Model model) {
        try {
            // Convert MultipartFile to Blob
            Blob imageBlob = imageFile.isEmpty() ? null : new javax.sql.rowset.serial.SerialBlob(imageFile.getBytes());

            // Create a new Movie object with the provided details
            Movie movie = new Movie();
            movie.setId_movie(idMovie);
            movie.setMovie_name(movieName);
            movie.setRating(rating);
            movie.setImage(imageBlob);

            // Call the repository method to update the movie
            movieRepository.updateMovie(movie);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
            model.addAttribute("error", "Error updating movie: " + e.getMessage());
            return "error_page"; // Redirect or show an error page
        }

        return "redirect:/httpmovies";
    }


/*
    @PostMapping("/saveAddedMovie")
    public String saveAddedMovie(Movie movie){
        movieRepository.saveSingleMovie(movie);
        return "redirect:/httpmovies";
    }*/

    @PostMapping("/saveAddedMovie")
    public String saveAddedMovie(@RequestParam("id_movie") int id_movie,
                                 @RequestParam("movie_name") String movieName,
                                 @RequestParam("rating") int rating,
                                 @RequestParam("image") MultipartFile imageFile) {
        try {
            // Create a new Movie object
            Movie movie = new Movie();
            movie.setMovie_name(movieName);
            movie.setRating(rating);

            // Set the image as Blob
            if (!imageFile.isEmpty()) {
                movie.setImage(new javax.sql.rowset.serial.SerialBlob(imageFile.getBytes()));
            }

            // Save the movie using the repository
            movieRepository.saveSingleMovie(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/httpmovies";
    }


    @GetMapping("/addnewmovie")
    public String addNewMovie(Model model){
        model.addAttribute("movie", new Movie());
        return "add_movie_form";
    }

    @PostMapping("/addtofavorite")
    public String showFavoritesMovies(@RequestParam("id")int id, Model model){
        Movie movie = movieRepository.getById(id);
       /// movieRepository.saveFavoriteMovie(new FavoriteMovie());
        FavoriteMovie favoriteMovie = new FavoriteMovie();
        favoriteMovie.setId_favorite_movie(movie.getId_movie());  // Set the movie ID as the favorite movie ID
        favoriteMovie.setMovie_name(movie.getMovie_name());       // Set the movie name
        favoriteMovie.setRating(movie.getRating());               // Set the rating
        favoriteMovie.setImage(movie.getImage());

        movieRepository.saveFavoriteMovie(favoriteMovie);

        return "redirect:/httpmovies";
    }
/*
    @PostMapping("/deletefavoritemovie")
    public String deleteFavoriteMovie(@RequestParam("id_favorite_movie") Long id_fm, Model model){
        FavoriteMovie favoriteMovie = movieRepository.getFavoriteMovieById(id_fm.byteValue());
        model.addAttribute("favorite_movie", favoriteMovie);
        return "delete_favorite_movie_form";
    }*/

    @PostMapping("/deletefavoritemovie")
    public String deleteFavoriteMovie(@RequestParam("id_favorite_movie") int id_favorite_movie, Model model) {
        // Fetch the movie by id and handle deletion logic
        FavoriteMovie favoriteMovie = movieRepository.getFavoriteMovieById(id_favorite_movie);
        model.addAttribute("favorite_movie", favoriteMovie);
        return "delete_favorite_movie_form";
    }

    @PostMapping("saveDeletedFavoriteMovie")
    public String saveDeletedFavoriteMovie(FavoriteMovie favoriteMovie){
        movieRepository.deleteFavoriteMovie(favoriteMovie);
        return "redirect:/favorites";
    }

/*


    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model){
        Movie movie = movieRepository.getById(id.byteValue());
        model.addAttribute("movie", movie);
        return "delete_movie_form";
    }*/


/*
    @PostMapping("/addtofavorite")
    public String saveFavoriteMovie(@RequestParam("id_movie") int idMovie,
                             @RequestParam("movie_name") String movieName,
                             @RequestParam("rating") int rating,
                             @RequestParam("image") MultipartFile imageFile,
                             Model model) {
        try {
            // Convert MultipartFile to Blob
            Blob imageBlob = imageFile.isEmpty() ? null : new javax.sql.rowset.serial.SerialBlob(imageFile.getBytes());

            // Create a new Movie object with the provided details
            Movie movie = new Movie();
            movie.setId_movie(idMovie);
            movie.setMovie_name(movieName);
            movie.setRating(rating);
            movie.setImage(imageBlob);

            // Call the repository method to update the movie
            movieRepository.saveFavoriteMovie(new FavoriteMovie());
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
            model.addAttribute("error", "Error updating movie: " + e.getMessage());
            return "error_page"; // Redirect or show an error page
        }

        return "redirect:/httpmovies";
    }*/





    @GetMapping("/favorites")
    public String getAllFavoritesMovies(Model model){
        List<FavoriteMovie>favoriteMovies = movieRepository.getAllFavoriteMovie();
        model.addAttribute("favorite_movie", favoriteMovies);
        return "showfavoritesmovies";
    }





/*

    @GetMapping("/httpmovies")
    public String getAllHttpMovies(Model model){
        List<Movie> movie = movieRepository.getAll();
        model.addAttribute("movie",movie);
        return "movies";
    }*/

/*
    @GetMapping("/getbymoviename")
    public String getByMovieName(@RequestParam("movie_name") String movie_name, Model model){
        Movie movie = movieRepository.getByName(movie_name);
        model.addAttribute("movie",movie);
        return "moviesbyname";
    }*/
/*
    @GetMapping("/moviesbyname")
    public String getMoviesByName(@RequestParam("movie_name") String movie_name, Model model){
        List<Movie> movie = (List<Movie>) movieRepository.getByName(movie_name);
        model.addAttribute("movie", movie);
        return "moviesbyname";
    }
*/

    @PostMapping("/searchMoviesByName")
    public String searchMovies(@RequestParam("movieName") String movieName, Model model) {
        List<Movie> movies = movieRepository.findByName(movieName);
        model.addAttribute("movie", movies);
        return "movies";
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public String handleMovieNotFoundException(MovieNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error_not_movie_found"; // Ensure you have an 'error.html' Thymeleaf template to display the error message
    }




    @PostMapping("/searchMoviesById")
    public String searchMovies(@RequestParam("id_movie") int id_movie, Model model) {
        List<Movie> movies = movieRepository.findById(id_movie);
        model.addAttribute("movie", movies);
        return "movies";
    }








}
