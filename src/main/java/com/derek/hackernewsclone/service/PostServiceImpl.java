package com.derek.hackernewsclone.service;

import com.derek.hackernewsclone.dao.PostRepository;
import com.derek.hackernewsclone.entity.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  private PostRepository postRepository;

  @Autowired
  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public List<Post> findAll() {
    return postRepository.findAll();
  }

  @Override
  public Post findById(int id) {
    Optional<Post> result = postRepository.findById(id);

    Post p = null;
    if (result.isPresent()) {
      p = result.get();
    } else {
      throw new RuntimeException("No post with that ID found!");
    }

    return p;
  }

  @Override
  public void save(Post p) {
    postRepository.save(p);
  }

  @Override
  public void deleteById(int id) {
    postRepository.deleteById(id);
  }
}
