package com.derek.hackernewsclone.dao;

import com.derek.hackernewsclone.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
  // Nothing!
}