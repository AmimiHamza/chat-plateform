package com.chat.demo.users;

import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;


@Controller
@AllArgsConstructor
public class UserController {
    private UserRepo userRepo;
    @GetMapping("/index")
    
    public String users(Model model) {
        System.out.println("hellooo");
        java.util.List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "users";
    }
    

}
