package pl.piogrammer.MovieLibrary;


import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.piogrammer.MovieLibrary.model.Movie;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("")
    public List<Movie> getAll(){
       return movieRepository.getAll();
    }

    @GetMapping("/allmovie")
    public List<Movie>getMovie(){ return movieRepository.getMovie();}

    @GetMapping("/movieweb")
    public ModelAndView getAllMovies() {
        ModelAndView modelAndView = new ModelAndView("movies");
        modelAndView.addObject("movies", movieRepository.getAll());
        return modelAndView;
    }
    @GetMapping("/{id}")
    public Movie getById(@PathVariable("id_movie") int id_movie){
        return movieRepository.getById(id_movie);
    }


    @GetMapping("/movie_name/{movie_name}")
    public Movie getByName(@PathVariable("movie_name") String movie_name){
        return movieRepository.getByName(movie_name);
    }

    @PostMapping("")
    public int add(@RequestBody List<Movie> movies){
        return movieRepository.save(movies);
    }

    @PutMapping("/{id_movie}")
    public int update(@PathVariable("id_movie") int id_movie, @RequestBody Movie updatedMovie){
        Movie movie = movieRepository.getById(id_movie);
        if(movie != null){
            movie.setMovie_name(updatedMovie.getMovie_name());
            movie.setRating(updatedMovie.getRating());

            movieRepository.update(movie);

            return 1;
        }else{
            return -1;
        }
    }
    @PatchMapping("/{id_movie}")
        public int partiallyUpdate(@PathVariable("id_movie") int id_movie, @RequestBody Movie updatedMovie){
            Movie movie = movieRepository.getById(id_movie);

            if(movie != null){
                if(updatedMovie.getMovie_name() != null) movie.setMovie_name(updatedMovie.getMovie_name());
                if(updatedMovie.getRating() > 0) movie.setRating(updatedMovie.getRating());

                movieRepository.update(movie);
                return 1;
            }else{
                return -1;
            }
        }

        @DeleteMapping("/{id}")
        public int delete(@PathVariable("id_movie") int id_movie){
        return movieRepository.delete(id_movie);
        }


    }

