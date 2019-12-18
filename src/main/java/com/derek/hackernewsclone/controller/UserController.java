package com.derek.hackernewsclone.controller;

import com.derek.hackernewsclone.entity.User;
import com.derek.hackernewsclone.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  public String registerNewUser(@ModelAttribute User u, Model theModel, HttpSession session) {
    if (userService.existsByUsername(u.getUsername())) {
      theModel.addAttribute("error", "Username already exists!");
      return "error";
    } else if (u.getUsername().length() < 3 || u.getUsername().length() > 12) {
      theModel.addAttribute("error", "Username is not the right length!");
      return "error";
    }

    String username = u.getUsername();
    String password = encoder.encode(u.getPassword());

    User encryptedUser = new User(username, password);

    userService.save(encryptedUser);
    session.setAttribute("username", u.getUsername());
    return "redirect:/home";
  }

  @PostMapping("/login")
  public String userLogin(@ModelAttribute User u, HttpSession session, Model theModel) {
    User foundUser = userService.getUserByUsername(u.getUsername());

    if (userService.existsByUsername(u.getUsername()) &&
      encoder.matches(u.getPassword(), foundUser.getPassword())) {
        session.setAttribute("username", u.getUsername());
        return "redirect:/home";
    } else {
      theModel.addAttribute("error", "Invalid login credentials");
      return "error";
    }
  }

  @RequestMapping("/logout")
  public String userLogout(HttpSession session, HttpServletRequest req, Model theModel) {

    if (session.getAttribute("username") != null) {
      session.removeAttribute("username");
      return "redirect:/home";
    } else {
      theModel.addAttribute("error", "You are not logged in!");
      return "error";
    }
  }
}