package pl.piogrammer.MovieLibrary.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Movie {

    private int id;
    private String name;
    private int rating;


}
