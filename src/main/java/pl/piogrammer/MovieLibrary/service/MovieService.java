package pl.piogrammer.MovieLibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.piogrammer.MovieLibrary.MovieRepository;
import pl.piogrammer.MovieLibrary.model.Movie;

@Service
public class MovieService {

    @Autowired
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Existing methods...

    public boolean saveMovieIfNotExists(Movie movie) {
        if (!movieRepository.movieExistsByName(movie.getMovie_name())) {
            movieRepository.saveSingleMovie(movie);
            return true;
        }
        return false;
    }
}
