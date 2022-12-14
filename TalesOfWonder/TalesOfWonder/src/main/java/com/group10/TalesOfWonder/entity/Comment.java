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
    @JoinColumn(name="comic_id", nullable=false)
    private Comic comic;
    @ManyToOne
    @JoinColumn(name="comment_id", nullable=true)
    private Comment commentParent;
}
