package com.derek.hackernewsclone.service;

import com.derek.hackernewsclone.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

  public User getUserById(int id);

  public void save(User u);

  public void deleteById(int id);

  public boolean existsByUsername(String username);

}
