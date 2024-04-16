package pl.piogrammer.MovieLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.piogrammer.MovieLibrary.model.Movie;
import pl.piogrammer.MovieLibrary.model.Ticket;
import pl.piogrammer.MovieLibrary.model.User;

import java.util.List;

@RestController
@RequestMapping("/tickets")

public class TicketController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("")
    public List<Movie> getAll(){
        return movieRepository.getAll();
    }




}
