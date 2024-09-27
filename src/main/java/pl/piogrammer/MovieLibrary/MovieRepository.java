package pl.piogrammer.MovieLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.piogrammer.MovieLibrary.Exceptions.MovieNotFoundException;
import pl.piogrammer.MovieLibrary.model.FavoriteMovie;
import pl.piogrammer.MovieLibrary.model.Movie;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {

    List<Object> savedMovies = new ArrayList<Object>();
    @Autowired
    JdbcTemplate jdbcTemplate;
    MovieNotFoundException movieNotFoundException;

    public List<Movie> getAll(){
        List<Movie> movies = jdbcTemplate.query("SELECT id_movie, movie_name, rating, image FROM movie", BeanPropertyRowMapper.newInstance(Movie.class));
        for (Movie movie : movies) {
            movie.setImageBase64(); // Set Base64 image data
        }

        return movies;
    }

    public List<FavoriteMovie> getAllFavoriteMovie(){
        List<FavoriteMovie> favoriteMovies = jdbcTemplate.query("SELECT * from favorite_movie", BeanPropertyRowMapper.newInstance(FavoriteMovie.class));
        for(FavoriteMovie favoriteMovie : favoriteMovies){
            favoriteMovie.setImageBase64();
        }
        return favoriteMovies;
    }

    public List<Movie> getMovie(){
        return jdbcTemplate.query("select * from movie where id_movie>5", BeanPropertyRowMapper.newInstance(Movie.class));
    }


    public Movie getById(int id_movie){
        return jdbcTemplate.queryForObject("SELECT id_movie, movie_name, rating FROM movie WHERE " + "id_movie=?",
                BeanPropertyRowMapper.newInstance(Movie.class), id_movie);
    }

    public FavoriteMovie getFavoriteMovieById(int id_favorite_movie){
        return jdbcTemplate.queryForObject("SELECT id_favorite_movie, movie_name, rating FROM favorite_movie WHERE " + "id_favorite_movie=?",
                BeanPropertyRowMapper.newInstance(FavoriteMovie.class), id_favorite_movie);
    }


    public Movie getByName(String name){
        return jdbcTemplate.queryForObject("SELECT id_movie, movie_name, rating FROM movie WHERE " + "name=?",
                BeanPropertyRowMapper.newInstance(Movie.class), name);

    }

    public boolean movieExistsByName(String movieName) {
        String sql = "SELECT COUNT(*) FROM movie WHERE movie_name = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, movieName);
        return count != null && count > 0;
    }


    public int save(List<Movie> movies) {
        movies.forEach(movie -> jdbcTemplate
                .update("INSERT INTO movie(movie_name, rating) VALUES(?, ?)",
                        movie.getMovie_name(), movie.getRating()
                ));
        return 1;
    }
/*
    public int saveFavoriteMovie(List<FavoriteMovie>favoriteMovies){
        favoriteMovies.forEach(favoriteMovie -> jdbcTemplate.update("INSERT INTO favorite_movie(id_favorite_movie, movie_name, rating, image, id_movie) VALUES(?, ?, ?, ?, ?)"));

        return 1;
    }

*/


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

    public int deleteFavoriteMovie(FavoriteMovie favoriteMovie){
        jdbcTemplate.update("DELETE FROM favorite_movie WHERE id_favorite_movie=?",
                favoriteMovie.getId_favorite_movie());
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
/*
    public int saveFavoriteMovie(FavoriteMovie favoriteMovie) {
        int rowsAffected = jdbcTemplate.update(
                "INSERT INTO favorite_movie(id_favorite_movie,  movie_name, rating, image) VALUES (?, ?, ?, ?)",
                favoriteMovie.getId_favorite_movie(),
                favoriteMovie.getMovie_name(),
                favoriteMovie.getRating(),
                favoriteMovie.getImage()

        );
        return rowsAffected;
    }*/


    public int saveFavoriteMovie(FavoriteMovie favoriteMovie) {
        int rowsAffected = jdbcTemplate.update(
                "INSERT INTO favorite_movie(id_favorite_movie,  movie_name, rating, image) VALUES (?, ?, ?, ?)",
                favoriteMovie.getId_favorite_movie(),
                favoriteMovie.getMovie_name(),
                favoriteMovie.getRating(),
                favoriteMovie.getImage()

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
/*

this method is used to only return movies by name it doesnt response any expetion when
movie with specified name doesnt exist in  db
    public List<Movie> findByName(String movieName) {
        String sql = "SELECT id_movie, movie_name, rating, image FROM movie WHERE movie_name LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + movieName + "%"}, BeanPropertyRowMapper.newInstance(Movie.class));
    }*/
///this method find movie by name and throw expeption when movie with specified name doesnt exist
    public List<Movie> findByName(String movieName) {
        String sql = "SELECT id_movie, movie_name, rating, image FROM movie WHERE movie_name LIKE ?";
        List<Movie> movies = jdbcTemplate.query(sql, new Object[]{"%" + movieName + "%"}, BeanPropertyRowMapper.newInstance(Movie.class));

        if (movies.isEmpty()) {
            throw new MovieNotFoundException("No movies found with the name: " + movieName);
        }

        return movies;
    }


    public List<Movie> findById(int id_movie){
        String sql = "SELECT id_movie, movie_name, rating, image FROM movie WHERE id_movie LIKE ?";
        List<Movie> movies = jdbcTemplate.query(sql, new Object[]{"%" + id_movie + "%"}, BeanPropertyRowMapper.newInstance(Movie.class));

        if(movies.isEmpty()){
            throw new MovieNotFoundException("No movies found with the id: " + id_movie);
        }
        return movies;

    }
/*
this method is used to only return movies by name it doesnt response any expetion when
movie with specified name doesnt exist in  db

    public List<Movie> findById(int id_movie){
        String sql = "SELECT id_movie, movie_name, rating, image FROM movie WHERE id_movie LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + id_movie + "%"}, BeanPropertyRowMapper.newInstance(Movie.class));
    }*/





}
