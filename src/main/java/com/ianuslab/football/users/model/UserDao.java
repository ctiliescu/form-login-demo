package com.ianuslab.football.users.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public UserDao() {
    }

    public UserDao(CreateUserRequest createUserRequest) {
        this.firstName = createUserRequest.getFirstName();
        this.lastName = createUserRequest.getLastName();
        this.password = createUserRequest.getPassword();
        this.email = createUserRequest.getEmail();
    }
}
