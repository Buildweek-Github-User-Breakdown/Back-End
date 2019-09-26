package com.lambdaschool.githubuser.repository;

import com.lambdaschool.githubuser.models.UserNotes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserNotesRepository extends CrudRepository<UserNotes, Long>
{
    List<UserNotes> findAllByUser_Username(String name);
}
