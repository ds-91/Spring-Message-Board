package com.derek.hackernewsclone.controller;

import com.derek.hackernewsclone.entity.Post;
import com.derek.hackernewsclone.entity.Reply;
import com.derek.hackernewsclone.service.PostService;
import com.derek.hackernewsclone.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReplyController {

  private ReplyService replyService;
  private PostService postService;

  @Autowired
  private ReplyController(ReplyService replyService, PostService postService) {
    this.replyService = replyService;
    this.postService = postService;
  }

  @GetMapping("/post/reply")
  public String showReplyPage(@RequestParam("post_id") int id, Model theModel) {
    Post p = postService.findById(id);

    theModel.addAttribute("post", p);
    return "reply";
  }

  @PostMapping("/reply")
  public String createNewReply(@RequestParam("post_id") int id, @ModelAttribute Reply reply,
      BindingResult br, Model theModel) {

    Reply r = new Reply(id, reply.getBody());
    replyService.save(r);

    return "redirect:/post/" + id;
  }

}