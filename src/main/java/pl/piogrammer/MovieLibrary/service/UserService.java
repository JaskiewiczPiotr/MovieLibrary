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


    public User registerUser(String user_name, String mail, String password) {
        if (user_name == null || password == null) {
            return null;
        } else {
            User user = new User();
            user.setUser_name(user_name);
            user.setMail(mail);
            user.setPassword(password);
            userRepo.save(user);
            return userRepo.save(user);
        }
    } 
}
