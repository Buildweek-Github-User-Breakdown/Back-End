package com.lambdaschool.githubuser.models;

import javax.persistence.*;

@Entity
@Table(name = "githubuser")
public class GithubUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long githubuserid;

    @Column(nullable = false,
            unique = true)
    private String login;

//    private String email;
//    private String company;
//
//    private String location;
//
//    private String created_at;

    public GithubUser() {
    }

    public GithubUser(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getCompany() {
//        return company;
//    }
//
//    public void setCompany(String company) {
//        this.company = company;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getCreated_at() {
//        return created_at;
//    }
//
//    public void setCreated_at(String created_at) {
//        this.created_at = created_at;
//    }
}
