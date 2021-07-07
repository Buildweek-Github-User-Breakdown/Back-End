package com.lambdaschool.githubuser.services;

import com.lambdaschool.githubuser.models.User;
import com.lambdaschool.githubuser.models.UserNotes;

import java.util.List;

public interface UserNotesService
{
    List<UserNotes> findAll();

    UserNotes findUserNotesById(long id);

    List<UserNotes> findByUserName(String username);

    void delete(long id);

//    User update(UserNotes userNotes, long id);

    UserNotes save(UserNotes userNotes);
}
