package pl.piogrammer.MovieLibrary.service;

import org.springframework.stereotype.Service;
import pl.piogrammer.MovieLibrary.model.User;
import pl.piogrammer.MovieLibrary.repository.UserRepo;

@Service
public class UserService {
    
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public User registerUser(String login, String mail, String password) {
        if (login == null || password == null) {
            return null;
        } else {
            User user = new User();
            user.setLogin(login);
            user.setMail(mail);
            user.setPassword(password);
            userRepo.save(user);
            return userRepo.save(user);
        }
    }

    public User authenticate(String login, String password){
        return (User) userRepo.findByLoginAndPassword(login, password).orElse(null);
    }
}

