package com.chat.demo.users;

import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


@Controller
@AllArgsConstructor 
public class UserController {
    private UserRepo userRepo;

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/")
    public String in() {
        return "index";
    }
    

    @GetMapping("/users")
    public String users(Model model) {
        java.util.List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/template1")
    public String navbar() {
        return "template1";
    }
    @GetMapping("/userinfo")
    public String userinfo() {
        return "userinfo";
    }

    @GetMapping("/adduser")
    public String adduser(Model model) {
        model.addAttribute("user", new User());
        System.out.println("hello");
        return "adduser";
    }

    @PostMapping("/save")
    public String login(Model model,@Valid User user,BindingResult bindingResult) { 
        if(bindingResult.hasErrors()) return "adduser" ;          
        userRepo.save(user);
        return "users";
}}
