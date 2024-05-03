package pl.piogrammer.MovieLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piogrammer.MovieLibrary.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {


}
