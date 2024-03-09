package com.chat.demo.users;

import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


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
    public String adduser() {
        return "adduser";
    }

    @PostMapping("/save")
    public String login(Model model,User user,@RequestParam("name") String name,@RequestParam("email") String email, 
    @RequestParam("password") String password,@RequestParam("role") String role) {
                            System.out.println("email");
                            System.out.println(email);
                            userRepo.save(new User(null,name,email,password,role));
                            return "index";
        
    

}}
