package pl.piogrammer.MovieLibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.piogrammer.MovieLibrary.model.User;
import pl.piogrammer.MovieLibrary.service.UserService;

@Controller  // Specify that this is a controller
public class RegisterController {


    @Autowired
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new User());
        return "register_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        System.out.println("register request" + user);
        User registeredUser = userService.registerUser(user.getLogin(), user.getPassword(), user.getMail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }
}
