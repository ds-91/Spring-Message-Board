package com.derek.hackernewsclone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="user_name")
  private String userName;

  @Column(name="title")
  private String title;

  @Column(name="body")
  private String body;

  public Post() {}

  public Post(int id, String userName, String title, String body) {
    this.id = id;
    this.userName = userName;
    this.title = title;
    this.body = body;
  }

  public Post(String userName, String title, String body) {
    this.userName = userName;
    this.title = title;
    this.body = body;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String toString() {
    return "ID: " + this.id + "\n" +
        "userName: " + this.userName + "\n" +
        "Title: " + this.title + "\n" +
        "Body: " + this.body;
  }
}
