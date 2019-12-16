package com.derek.hackernewsclone.service;

import com.derek.hackernewsclone.entity.Reply;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ReplyService {

  public List<Reply> findAllByPostId(int id);

  public Reply findById(int id);

  public void save(Reply r);
}
