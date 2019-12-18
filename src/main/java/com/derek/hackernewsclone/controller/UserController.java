package com.derek.hackernewsclone.controller;

import com.derek.hackernewsclone.entity.User;
import com.derek.hackernewsclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

  private UserService userService;

  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

  @Autowired
  public UserController(UserService userService) {
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

    String username = u.getUsername();
    String password = encoder.encode(u.getPassword());

    User encryptedUser = new User(username, password);

    userService.save(encryptedUser);
    return "redirect:/home";
  }


  @RequestMapping("/login")
  public String userLogin(@ModelAttribute User u, BindingResult br, Model theModel) {
    User foundUser = null;
    String errorMsg = null;
    if (br.hasErrors()) {
      return "register-error";
    }

    foundUser = userService.getUserByUsername(u.getUsername());

    if (foundUser != null) {
      if (encoder.matches(u.getPassword(), foundUser.getPassword())) {
        // log in
        errorMsg = "Logged in!";
        theModel.addAttribute("error", errorMsg);
        return "redirect:/home";
      } else {
        errorMsg = "Invalid password";
        theModel.addAttribute("error", errorMsg);
        return "error";
      }
    } else {
      errorMsg = "User not found!";
      theModel.addAttribute("error", errorMsg);
      return "error";
    }
  }
}
