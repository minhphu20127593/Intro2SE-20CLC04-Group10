package com.group10.TalesOfWonder.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200,nullable = false,columnDefinition = "nvarchar")
    private String name;
    private Date dateModified;
    private Integer countView;
    @ManyToOne
    @JoinColumn(name="comic_id", nullable=false)
    private Comic comic;
}


//@Entity
//@Table(name = "comments")
//public class Comment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @Column(length = 200,nullable = false,columnDefinition = "nvarchar")
//    private String content;
//    private Date dateComment;