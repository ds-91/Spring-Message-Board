package com.derek.hackernewsclone.controller;

import com.derek.hackernewsclone.entity.Post;
import com.derek.hackernewsclone.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PostController {

  private PostService postService;

  @Autowired
  private PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("/home")
  public String showHomePage(Model theModel) {
    List<Post> allPosts = postService.findAll();

    theModel.addAttribute("posts", allPosts);

    return "home";
  }

  @GetMapping("/post/new")
  public String showNewPostPage() {
    return "newpost";
  }

  @PostMapping("/post/new")
  public String createNewPost(@ModelAttribute Post post, BindingResult br, Model theModel) {
    if (br.hasErrors()) {
      return "error";
    }

    postService.save(post);
    return "redirect:/home";
  }

  @GetMapping("/post/{id}")
  public String showPostByIdPage(@PathVariable(value="id") int id, Model theModel) {
    Post p = postService.findById(id);
    theModel.addAttribute("posts", p);

    return "postview";
  }
}
