package com.derek.hackernewsclone.dao;

import com.derek.hackernewsclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByUsername(String username);

  User findUserByUsername(String username);
}
