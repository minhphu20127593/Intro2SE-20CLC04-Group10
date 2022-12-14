package com.group10.TalesOfWonder.entity;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer orderInChapter;
    @Column(length = 200,nullable = false)
    private String pathImage;
    @ManyToOne
    @JoinColumn(name="chapter_id", nullable=false)
    private Chapter chapter;
}
