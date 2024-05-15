package pl.piogrammer.MovieLibrary.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Movie {

    @Id

    private int id_movie;
    private String movie_name;
    private int rating;




}
