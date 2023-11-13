package com.rakesh.server.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(nullable = false)
    private String firstName;

    @NotEmpty
    @Column(nullable = false,unique = true)
    @Email(message = "{errors.invalid_email}")
    private String email;

    @NotEmpty
    @Column(nullable = false,unique = true)
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID",referencedColumnName = "ID")}
    )
    private List<Role> roles;
    public User(User user)
    {
        this.firstName=user.getFirstName();
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.roles=user.getRoles();
    }
    public User()
    {

    }



}
