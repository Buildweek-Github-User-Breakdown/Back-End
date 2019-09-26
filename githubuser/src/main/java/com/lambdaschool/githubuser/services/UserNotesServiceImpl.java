package com.lambdaschool.githubuser.services;

import com.lambdaschool.githubuser.exceptions.ResourceNotFoundException;
import com.lambdaschool.githubuser.models.UserNotes;
import com.lambdaschool.githubuser.repository.UserNotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "usernotesService")
public class UserNotesServiceImpl implements UserNotesService
{
    @Autowired
    private UserNotesRepository usernotesrepos;

    @Override
    public List<UserNotes> findAll()
    {
        List<UserNotes> list = new ArrayList<>();
        usernotesrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public UserNotes findUsernotesById(long id)
    {
        return usernotesrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " Not Found!"));
    }

    @Override
    public List<UserNotes> findByUserNotes(String username)
    {
        return usernotesrepos.findAllByUser_Username(username);
    }

    @Override
    public void delete(long id)
    {
        if (usernotesrepos.findById(id)
                .isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext()
                    .getAuthentication();
            if (usernotesrepos.findById(id)
                    .get()
                    .getUser()
                    .getUsername()
                    .equalsIgnoreCase(authentication.getName()))
            {
                usernotesrepos.deleteById(id);
            } else
            {
                throw new ResourceNotFoundException(authentication.getName() + " not authorized to make change");
            }
        } else
        {
            throw new ResourceNotFoundException("User with id " + id + " Not Found!");
        }
    }

    @Override
    public UserNotes save(UserNotes userNotes)
    {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (userNotes.getUser()
                .getUsername()
                .equalsIgnoreCase(authentication.getName()))
        {
            return usernotesrepos.save(userNotes);
        }
        return usernotesrepos.save(userNotes);
    }
}