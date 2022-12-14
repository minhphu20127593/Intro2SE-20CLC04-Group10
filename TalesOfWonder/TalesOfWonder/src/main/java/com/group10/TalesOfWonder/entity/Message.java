package com.group10.TalesOfWonder.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date dateSend;
    @Column(length = 100,nullable = false,columnDefinition = "nvarchar")
    private String title;
    @Column(length = 200,nullable = false,columnDefinition = "nvarchar")
    private String content;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "messages_users",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public Set<User> users = new HashSet<>();
}
