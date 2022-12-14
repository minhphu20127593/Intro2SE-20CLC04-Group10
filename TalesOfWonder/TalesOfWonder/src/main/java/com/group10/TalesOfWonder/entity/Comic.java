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
    @Column(length = 100,nullable = false,unique = true,columnDefinition = "nvarchar")
    private String name;
    private Date datePost;
    private Date lastModified;
    @Column(length = 100,nullable = true)
    private String author;
    @Column(length = 100,nullable = false)
    private String status;
    private Integer countView;
    @Column(length = 500,nullable = false,columnDefinition = "nvarchar")
    private String description;
    @Column(length = 100,nullable = true,columnDefinition = "nvarchar")
    private String anotherName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "comics_votes",
            joinColumns = @JoinColumn(name = "comic_id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id")
    )
    public Set<Vote> votes = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "comics_authors",
            joinColumns = @JoinColumn(name = "comic_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    public Set<Author> authors = new HashSet<>();
}
