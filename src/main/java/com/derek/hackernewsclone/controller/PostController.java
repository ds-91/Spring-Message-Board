package com.derek.hackernewsclone.controller;

import com.derek.hackernewsclone.entity.Post;
import com.derek.hackernewsclone.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/home")
public class PostController {

  private PostService postService;

  @Autowired
  private PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("/home")
  public String home(Model theModel) {
    List<Post> allPosts = postService.findAll();

    theModel.addAttribute("posts", allPosts);

    return "home";
  }

  @GetMapping("/post/{id}")
  public String getPostById(@PathVariable(value="id") int id, Model theModel) {
    Post p = postService.findById(id);
    theModel.addAttribute("posts", p);

    return "postview";
  }

  @GetMapping("/post")
  public String createPost() {
    return "newpost";
  }

}
