package pl.piogrammer.MovieLibrary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @PostMapping("")
    public int addNewUser(@RequestBody List<User> users){
        return userRepository.saveUser(users);
    }

    @PostMapping("/singleuser")
    public ResponseEntity<?> addSingleUser(@RequestBody User user) {
        try {
            // Save the user using the repository method
            userRepository.saveSingleUser(user);

            // Return a successful response with HTTP 201 Created status
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            // Handle any exceptions and return an appropriate HTTP 500 Internal Server Error status
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving user: " + e.getMessage());
        }
    }





}
