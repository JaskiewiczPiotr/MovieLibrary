package pl.piogrammer.MovieLibrary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piogrammer.MovieLibrary.model.User;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    @PostMapping("/adduser")
    public int addNewUser(@RequestBody List<User> users){
        return userRepository.saveUser(users);
    }


}
