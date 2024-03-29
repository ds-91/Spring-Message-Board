package com.derek.hackernewsclone.controller;

import com.derek.hackernewsclone.entity.Post;
import com.derek.hackernewsclone.entity.Reply;
import com.derek.hackernewsclone.entity.User;
import com.derek.hackernewsclone.service.PostService;
import com.derek.hackernewsclone.service.ReplyService;
import com.derek.hackernewsclone.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReplyController {

  private ReplyService replyService;
  private PostService postService;
  private UserService userService;

  @Autowired
  private ReplyController(ReplyService replyService, PostService postService, UserService userService) {
    this.replyService = replyService;
    this.postService = postService;
    this.userService = userService;
  }

  @GetMapping("/post/reply")
  public String showReplyPage(@RequestParam("post_id") int id, Model theModel) {
    Post p = postService.findById(id);

    theModel.addAttribute("post", p);
    return "reply";
  }

  @PostMapping("/reply")
  public String createNewReply(@RequestParam("post_id") int id, @ModelAttribute Reply reply,
      HttpSession session, Model theModel) {

    if (session.getAttribute("loggedin") == null) {
      theModel.addAttribute("error", "Not authorized");
      return "error";
    }

    User loggedInUsername = (User) session.getAttribute("loggedin");
    User tempUser = userService.findUserByUsername(loggedInUsername.getUsername());

    Reply r = new Reply(id, tempUser.getUsername(), reply.getBody());
    replyService.save(r);

    return "redirect:/post/" + id;
  }

}
