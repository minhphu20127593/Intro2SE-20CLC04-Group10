package com.group10.TalesOfWonder.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 300,nullable = false,unique = true)
    private String email;
    @Column(name = "full_name",length = 500,nullable = false,columnDefinition = "nvarchar")
    private String fullName;

    @Column(length = 400,nullable = false)
    private String password;
    private boolean enable;
    private String photos;
    private Date registerDate;
    @ManyToOne
    @JoinColumn(name="role_id", nullable=false)
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_comicsPublish",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "comic_id")
    )
    public Set<Comic> comicsPublish = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_comicsFollow",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "comic_id")
    )
    public Set<Comic> comicsFollow = new HashSet<>();

    public User(String email, String fullName, String password, boolean enable, Role role) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.enable = enable;
        this.role = role;
        this.enable = true;
        this.registerDate = new Date();
    }

    public String getFullName() {
        return fullName;
    }
    @Transient
    public String getPhotosImagePath() {
        if (id == null || photos == null) return  "/images/user.png";

        return "/user-photos/" + this.id + "/" + this.photos;
    }
    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getPhotos() {
        return photos;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public Role getRole() {
        return role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(String email, String fullName, String password, boolean enable, Date registerDate, Role role) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.enable = enable;
        this.registerDate = registerDate;
        this.role = role;
    }

    public User() {
        this.registerDate = new Date();
        this.enable = true;
    }
}
