package com.group10.TalesOfWonder.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comics")
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200,nullable = false,unique = true,columnDefinition = "nvarchar")
    private String name;
    private Date datePost;
    private Date lastModified;
    @Column(length = 100,nullable = false)
    private String status;
    private Integer countView;
    @Column(length = 1000,nullable = false,columnDefinition = "nvarchar")
    private String description;
    @Column(length = 100,nullable = true,columnDefinition = "nvarchar")
    private String anotherName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "comics_categories",
            joinColumns = @JoinColumn(name = "comic_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    public Set<Category> categories = new HashSet<>();
    private String authors;
    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private User creator;
    private String poster;
    private Boolean enable;
    public Comic(String name, Date datePost, String status, String description, String anotherName, Set<Category> categories, String authors) {
        this.name = name;
        this.datePost = datePost;
        this.status = status;
        this.description = description;
        this.anotherName = anotherName;
        this.categories = categories;
        this.authors = authors;
        this.datePost = new Date();
        this.lastModified = new Date();
        this.countView = 0;
    }

    public Comic() {
    }
    @Transient
    public String getPhotosImagePath() {
        if (id == null || poster == null) return  "/images/poster.png";

        return "/comic-poster/" + this.id + "/" + this.poster;
    }
    public String getPoster() {
        return poster;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "name='" + name + '\'' +
                ", datePost=" + datePost +
                ", lastModified=" + lastModified +
                ", status='" + status + '\'' +
                ", countView=" + countView +
                ", description='" + description + '\'' +
                ", anotherName='" + anotherName + '\'' +
                ", categories=" + categories +
                ", authors='" + authors + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCountView(Integer countView) {
        this.countView = countView;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }



    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getName() {
        return name;
    }

    public Date getDatePost() {
        return datePost;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCountView() {
        return countView;
    }

    public String getDescription() {
        return description;
    }

    public String getAnotherName() {
        return anotherName;
    }



    public Set<Category> getCategories() {
        return categories;
    }

    public String getAuthors() {
        return authors;
    }

    public void increaseContView() {
        countView++;
    }
}
