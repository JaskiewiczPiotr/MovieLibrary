package pl.piogrammer.MovieLibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private int id_user;
    private String username;
    private String mail;
    private String password;

}
