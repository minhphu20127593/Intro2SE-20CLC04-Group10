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

    public Image(Integer orderInChapter, String pathImage, Chapter chapter) {
        this.orderInChapter = orderInChapter;
        this.pathImage = pathImage;
        this.chapter = chapter;
    }
    @Transient
    public String getPhotosImagePath() {
        if (id == null || pathImage == null) return  "/images/poster.png";

        return "/comic-image/" + this.id + "/" + this.pathImage;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrderInChapter(Integer orderInChapter) {
        this.orderInChapter = orderInChapter;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOrderInChapter() {
        return orderInChapter;
    }

    public String getPathImage() {
        return pathImage;
    }

    public Chapter getChapter() {
        return chapter;
    }
}
