package com.derek.hackernewsclone.dao;

import com.derek.hackernewsclone.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
  // Nothing!
}
