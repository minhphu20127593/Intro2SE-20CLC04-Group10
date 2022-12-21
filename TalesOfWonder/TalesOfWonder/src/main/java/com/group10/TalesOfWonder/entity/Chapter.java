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
    private boolean enable;
    @ManyToOne
    @JoinColumn(name="comic_id", nullable=false)
    private Comic comic;

    public Chapter() {
    }

    public Chapter(String name, Date dateModified, Integer countView, boolean enable, Comic comic) {
        this.name = name;
        this.dateModified = dateModified;
        this.countView = countView;
        this.enable = enable;
        this.comic = comic;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public void setCountView(Integer countView) {
        this.countView = countView;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public Integer getCountView() {
        return countView;
    }

    public boolean isEnable() {
        return enable;
    }

    public Comic getComic() {
        return comic;
    }
}


