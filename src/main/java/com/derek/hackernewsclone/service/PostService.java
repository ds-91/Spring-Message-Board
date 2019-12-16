package com.derek.hackernewsclone.service;

import com.derek.hackernewsclone.entity.Post;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

  public List<Post> findAll();

  public Post findById(int id);

  public void save(Post p);

  public void deleteById(int id);

}
