package com.group10.TalesOfWonder.entity;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200,nullable = false,columnDefinition = "nvarchar",unique = true)
    private String name;
    @Column(length = 400,columnDefinition = "nvarchar")
    private String description;
    public Category(String name,String description) {
        this.name = name;
        this.description = description;
    }

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
