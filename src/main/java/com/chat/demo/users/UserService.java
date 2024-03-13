package com.chat.demo.users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.AllArgsConstructor;

import jakarta.validation.Valid;

import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepo userRepo;

    private User getCurrentUser() {
        return userRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public String in() {
        return "index";
    }

    public String index() {
        return "index";
    }

    public String users(Model model) {
        java.util.List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    public String navbar(Model model) {
        model.addAttribute("email", getCurrentUser().getEmail());
        return "template1";
    }

    public String userinfo() {
        return "userinfo";
    }

    public String adduser(Model model) {
        model.addAttribute("user", new User());
        return "adduser";
    }

    public String saveUser(Model model, @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "adduser";
        userRepo.save(user);
        return "users";
    }
}
