package pl.piogrammer.MovieLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piogrammer.MovieLibrary.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByLoginAndPassword(String login, String password);
}
