package com.derek.hackernewsclone.controller;

import com.derek.hackernewsclone.entity.Post;
import com.derek.hackernewsclone.entity.Reply;
import com.derek.hackernewsclone.entity.User;
import com.derek.hackernewsclone.service.PostService;
import com.derek.hackernewsclone.service.ReplyService;
import com.derek.hackernewsclone.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PostController {

  private PostService postService;
  private ReplyService replyService;
  private UserService userService;

  @Autowired
  private PostController(PostService postService, ReplyService replyService, UserService userService) {
    this.postService = postService;
    this.replyService = replyService;
    this.userService = userService;
  }

  @GetMapping("/home")
  public String showHomePage(Model theModel, HttpSession session) {
    List<Post> allPosts = postService.findAll();

    theModel.addAttribute("posts", allPosts);
    return "home";
  }

  @GetMapping("/post/new")
  public String showNewPostPage(Model theModel, HttpSession session) {
    if (session.getAttribute("loggedin") == null) {
      theModel.addAttribute("error", "You are not allowed to post without logging in!");
      return "error";
    } else {
      return "newpost";
    }
  }

  @PostMapping("/post/new")
  public String createNewPost(@ModelAttribute Post post, Model theModel, HttpServletRequest req, HttpSession session) {
    if (session.getAttribute("loggedin") == null) {
      theModel.addAttribute("error", "You are not allowed to post without logging in!");
      return "error";
    } else {
      User userLoggedIn = (User) session.getAttribute("loggedin");
      User newUser = userService.findUserByUsername(userLoggedIn.getUsername());

      Post newPost = new Post(newUser.getUsername(), post.getTitle(), post.getBody());
      postService.save(newPost);
      return "redirect:/home";
    }
  }

  @GetMapping("/post/{id}")
  public String showPostByIdPage(@PathVariable(value="id") int id, Model theModel) {
    Post p = postService.findById(id);
    List<Reply> replyList = replyService.findAllByPostId(id);

    theModel.addAttribute("replies", replyList);
    theModel.addAttribute("posts", p);

    return "postview";
  }
}
