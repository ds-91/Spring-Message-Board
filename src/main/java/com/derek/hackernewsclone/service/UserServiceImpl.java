package com.derek.hackernewsclone.service;

import com.derek.hackernewsclone.dao.UserRepository;
import com.derek.hackernewsclone.entity.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

  private UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findById(int id) {
    Optional<User> temp = userRepository.findById(id);

    if (temp == null) {
      throw new RuntimeException("Could not find user with that id!");
    } else {
      return temp.get();
    }
  }

  @Override
  public void save(User u) {
    userRepository.save(u);
  }

  @Override
  public void deleteById(int id) {
    userRepository.deleteById(id);
  }

  @Override
  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public User findUserByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }
}
