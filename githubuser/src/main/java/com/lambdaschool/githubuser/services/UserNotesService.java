package com.lambdaschool.githubuser.services;

import com.lambdaschool.githubuser.models.UserNotes;

import java.util.List;

public interface UserNotesService {

    List<UserNotes> findAll();

    UserNotes findUsernotesById(long id);

    List<UserNotes> findByUserNotes(String username);

    void delete(long id);

    UserNotes save(UserNotes userNotes);
}
