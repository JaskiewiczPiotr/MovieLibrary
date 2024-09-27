package pl.piogrammer.MovieLibrary.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.piogrammer.MovieLibrary.MovieRepository;
import pl.piogrammer.MovieLibrary.model.User;
import pl.piogrammer.MovieLibrary.service.UserService;
import pl.piogrammer.MovieLibrary.model.Movie;
@Controller
public class LoginController {


    @Autowired
    private final UserService userService;
    @Autowired
    MovieRepository movieRepository;

    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String getLoginRequest(Model model){
        model.addAttribute("loginRequest", new User());
        return "login_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model){
        System.out.println("login request" + user);
        User authenticated = userService.authenticate(user.getLogin(), user.getPassword());
        if(authenticated != null){
            model.addAttribute("userLogin", authenticated.getLogin());
            return "movies";
        }else{
            return "error_page";
        }
    }

}
