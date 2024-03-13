package com.chat.demo.users;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


@Controller
@AllArgsConstructor 
public class UserController {
    private UserRepo userRepo;
    
    private User getCurrentUser() {
        return userRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }



    @GetMapping("/")
    public String in() {
        return "index";
    }
    

    @GetMapping("/index")
public String index() {
    return "index";
}
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/users")
public String users(Model model) {
    java.util.List<User> users = userRepo.findAll();
    model.addAttribute("users", users);
    return "users";
}

@GetMapping("/template1")
public String navbar(Model model) {
    model.addAttribute("email",getCurrentUser().getEmail() );
    return "template1";
}

@GetMapping("/userinfo")
public String userinfo() {
    return "userinfo";
}
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/adduser")
public String adduser(Model model) {
    model.addAttribute("user", new User());
    return "adduser";
}


    @PostMapping("/save")
    public String login(Model model,@Valid User user,BindingResult bindingResult) { 
        if(bindingResult.hasErrors()) return "adduser" ;          
        userRepo.save(user);
        return "users";
}}
