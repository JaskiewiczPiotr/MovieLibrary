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
        return jdbcTemplate.query("SELECT id, name, rating FROM movie", BeanPropertyRowMapper.newInstance(Movie.class));
    }

    public List<Movie> getMovie(){
        return jdbcTemplate.query("select * from movie where id>5", BeanPropertyRowMapper.newInstance(Movie.class));
    }


    public Movie getById(int id){
        return jdbcTemplate.queryForObject("SELECT id, name, rating FROM movie WHERE " + "id=?",
                BeanPropertyRowMapper.newInstance(Movie.class), id);
    }

    public Movie getByName(String name){
        return jdbcTemplate.queryForObject("SELECT id, name, rating FROM movie WHERE " + "name=?",
                BeanPropertyRowMapper.newInstance(Movie.class), name);

    }


    public int save(List<Movie> movies) {
        movies.forEach(movie -> jdbcTemplate
                .update("INSERT INTO movie(name, rating) VALUES(?, ?)",
                        movie.getName(), movie.getRating()
                ));
        return 1;
    }


    public int update(Movie movie){
        jdbcTemplate.update("UPDATE movie SET name=?, rating=? WHERE id=?",
                movie.getName(), movie.getRating(), movie.getId());
        return 1;
    }

    public int delete(int id){
        return  jdbcTemplate.update("DELETE FROM movie WHERE id=?", id);
    }

}
