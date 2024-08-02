package pl.piogrammer.MovieLibrary.Exceptions;

public class MovieNotFoundException  extends RuntimeException{
    public MovieNotFoundException(String message){
        super(message);
    }
}
