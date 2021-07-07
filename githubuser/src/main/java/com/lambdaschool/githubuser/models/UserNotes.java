package com.lambdaschool.githubuser.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "usernotes")
public class UserNotes extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long usernotesid;

    @Column
    private String usernotes;

    @ManyToOne
    @JoinColumn(name = "userid",
                nullable = false)
    @JsonIgnoreProperties("usernotes")
    private User user;

    public UserNotes()
    {
    }

    public UserNotes(User user, String usernotes)
    {
        this.usernotes = usernotes;
        this.user = user;
    }

    public long getUsernotesid()
    {
        return usernotesid;
    }

    public void setUsernotesid(long usernotesid)
    {
        this.usernotesid = usernotesid;
    }

    public String getUsernotes()
    {
        return usernotes;
    }

    public void setUsernotes(String usernotes)
    {
        this.usernotes = usernotes;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserNotes{" +
                "usernotesid=" + usernotesid +
                ", usernotes='" + usernotes + '\'' +
                ", user=" + user +
                '}';
    }
}
