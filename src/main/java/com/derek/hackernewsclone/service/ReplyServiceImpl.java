package com.derek.hackernewsclone.service;

import com.derek.hackernewsclone.dao.ReplyRepository;
import com.derek.hackernewsclone.entity.Reply;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

  private ReplyRepository replyRepository;

  @Autowired
  public ReplyServiceImpl(ReplyRepository replyRepository) {
    this.replyRepository = replyRepository;
  }

  @Override
  public List<Reply> findAllByPostId(int id) {
    return null;
  }

  @Override
  public Reply findById(int id) {
    return null;
  }

  @Override
  public void save(Reply r) {
    replyRepository.save(r);
  }
}
