package pl.piogrammer.MovieLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.piogrammer.MovieLibrary.model.Movie;
import pl.piogrammer.MovieLibrary.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {

    List<Object> savedMovies = new ArrayList<Object>();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movie> getAll(){
        List<Movie> movies = jdbcTemplate.query("SELECT id_movie, movie_name, rating, image FROM movie", BeanPropertyRowMapper.newInstance(Movie.class));
        for (Movie movie : movies) {
            movie.setImageBase64(); // Set Base64 image data
        }

        return movies;
    }

    public List<Movie> getMovie(){
        return jdbcTemplate.query("select * from movie where id_movie>5", BeanPropertyRowMapper.newInstance(Movie.class));
    }


    public Movie getById(int id_movie){
        return jdbcTemplate.queryForObject("SELECT id_movie, movie_name, rating FROM movie WHERE " + "id_movie=?",
                BeanPropertyRowMapper.newInstance(Movie.class), id_movie);
    }


    public Movie getByName(String name){
        return jdbcTemplate.queryForObject("SELECT id_movie, movie_name, rating FROM movie WHERE " + "name=?",
                BeanPropertyRowMapper.newInstance(Movie.class), name);

    }


    public int save(List<Movie> movies) {
        movies.forEach(movie -> jdbcTemplate
                .update("INSERT INTO movie(movie_name, rating) VALUES(?, ?)",
                        movie.getMovie_name(), movie.getRating()
                ));
        return 1;
    }


    public int update(Movie movie){
        jdbcTemplate.update("UPDATE movie SET movie_name=?, rating=? WHERE id=?",
                movie.getMovie_name(), movie.getRating(), movie.getId_movie());
        return 1;
    }

    public int delete(Movie movie){
          jdbcTemplate.update("DELETE FROM movie WHERE id_movie=?",
                movie.getId_movie());
          return 1;


    }


    public int deleteMovie(Movie movie) {
        int rowsAffected = jdbcTemplate.update("DELETE FROM movie WHERE id_movie = ?", getById(121)
                );
        return rowsAffected;
    }

    public int saveSingleMovie(Movie movie) {
        int rowsAffected = jdbcTemplate.update(
                "INSERT INTO movie(id_movie, movie_name, rating, image) VALUES (?, ?, ?, ?)",
                movie.getId_movie(),
                movie.getMovie_name(),
                movie.getRating(),
                movie.getImage()

        );
        return rowsAffected;
    }

    public int updateMovie(Movie movie) {
        int rowsAffected = jdbcTemplate.update(
                "UPDATE movie SET movie_name = ?, rating = ?, image = ? WHERE id_movie = ?",
                movie.getMovie_name(),
                movie.getRating(),
                movie.getImage() != null ? movie.getImage() : null, // Handle the case where the image might be null
                movie.getId_movie()
        );
        return rowsAffected;
    }

}
