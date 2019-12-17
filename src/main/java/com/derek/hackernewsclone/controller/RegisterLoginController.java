package com.derek.hackernewsclone.controller;

import com.derek.hackernewsclone.entity.User;
import com.derek.hackernewsclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterLoginController {

  private UserService userService;

  @Autowired
  public RegisterLoginController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register")
  public String register() {
    return "register_login";
  }

  @PostMapping("/register")
  public String registerNewUser(@ModelAttribute User u, BindingResult br) {
    if (br.hasErrors()) {
      return "register-error";
    }
    if (userService.existsByUsername(u.getUsername())) {
      return "register-error";
    }

    userService.save(u);
    return "redirect:/home";
  }
}
