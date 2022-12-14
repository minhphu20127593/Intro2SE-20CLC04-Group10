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
    @Column(length = 125,nullable = false,unique = true)
    private String email;
    @Column(name = "first_name",length = 64,nullable = false,columnDefinition = "nvarchar")
    private String firstname;
    @Column(name = "last_name",length = 64,nullable = false,columnDefinition = "nvarchar")
    private String lastname;

    @Column(length = 64,nullable = false)
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

    public User(String email, String firstname, String lastname, String password, boolean enable, Role role) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.enable = enable;
        this.role = role;
        this.enable = true;
        this.registerDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", enable=" + enable +
                ", registerDate=" + registerDate +
                ", role=" + role +
                '}';
    }
}
