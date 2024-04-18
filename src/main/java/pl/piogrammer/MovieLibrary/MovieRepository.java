package pl.piogrammer.MovieLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.piogrammer.MovieLibrary.model.Movie;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {

    List<Object> savedMovies = new ArrayList<Object>();
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movie> getAll(){
        return jdbcTemplate.query("SELECT id_movie, movie_name, rating FROM movie", BeanPropertyRowMapper.newInstance(Movie.class));
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

    public int delete(int id_movie){
        return  jdbcTemplate.update("DELETE FROM movie WHERE id_movie=?", id_movie);
    }

}
