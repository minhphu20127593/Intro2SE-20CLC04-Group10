package com.group10.TalesOfWonder.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200,nullable = false,columnDefinition = "nvarchar")
    private String content;
    private Date dateComment;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name="comic_id", nullable=true)
    private Comic comic;
    @ManyToOne
    @JoinColumn(name="chapter_id", nullable=true)
    private Chapter chapter;
    @ManyToOne
    @JoinColumn(name="comment_id", nullable=true)
    private Comment commentParent;

    public Comment() {

    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Comment(String content, Date dateComment, User user, Comic comic, Comment commentParent) {
        this.content = content;
        this.dateComment = dateComment;
        this.user = user;
        this.comic = comic;
        this.commentParent = commentParent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public Comment getCommentParent() {
        return commentParent;
    }

    public void setCommentParent(Comment commentParent) {
        this.commentParent = commentParent;
    }
}
