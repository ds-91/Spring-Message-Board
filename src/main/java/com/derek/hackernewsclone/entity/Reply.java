package com.derek.hackernewsclone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reply")
public class Reply {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="user_name")
  private String userName;

  @Column(name="post_id")
  private int postId;

  @Column(name="body")
  private String body;

  public Reply() {}

  public Reply(int id, String userName, int postId, String body) {
    this.id = id;
    this.userName = userName;
    this.postId = postId;
    this.body = body;
  }

  public Reply(int postId, String userName, String body) {
    this.postId = postId;
    this.userName = userName;
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

  public int getPostId() {
    return postId;
  }

  public void setPostId(int postId) {
    this.postId = postId;
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
        "Post ID: " + this.postId + "\n" +
        "Body: " + this.body + "\n";
  }
}
