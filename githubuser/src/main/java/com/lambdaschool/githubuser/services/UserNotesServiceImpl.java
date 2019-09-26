package com.lambdaschool.githubuser.services;

import com.lambdaschool.githubuser.exceptions.ResourceFoundException;
import com.lambdaschool.githubuser.exceptions.ResourceNotFoundException;
import com.lambdaschool.githubuser.models.User;
import com.lambdaschool.githubuser.models.UserNotes;
import com.lambdaschool.githubuser.repository.UserNotesRepository;
import com.lambdaschool.githubuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "usernotesService")
public class UserNotesServiceImpl implements UserNotesService
{
    @Autowired
    private UserNotesRepository usernotesrepoistory;

    @Autowired
    private UserRepository userrepos;

    @Override
    public List<UserNotes> findAll()
    {
        List<UserNotes> list = new ArrayList<>();
        usernotesrepoistory.findAll()
                      .iterator()
                      .forEachRemaining(list::add);
        return list;
    }

    @Override
    public UserNotes findUserNotesById(long id)
    {
        return usernotesrepoistory.findById(id)
                             .orElseThrow(() -> new ResourceNotFoundException("Useremail with id " + id + " Not Found!"));
    }

    @Override
    public List<UserNotes> findByUserName(String username)
    {
        return usernotesrepoistory.findAllByUser_Username(username);
    }

    @Override
    public void delete(long id)
    {
        if (usernotesrepoistory.findById(id)
                          .isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext()
                                                                 .getAuthentication();
            if (usernotesrepoistory.findById(id)
                              .get()
                              .getUser()
                              .getUsername()
                              .equalsIgnoreCase(authentication.getName()))
            {
                usernotesrepoistory.deleteById(id);
            } else
            {
                throw new ResourceNotFoundException(authentication.getName() + " not authorized to make change");
            }
        } else
        {
            throw new ResourceNotFoundException("Useremail with id " + id + " Not Found!");
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
            return usernotesrepoistory.save(userNotes);
        } else
        {
            throw new ResourceNotFoundException((authentication.getName() + "not authorized to make change"));
        }
    }

//    @Transactional
//    @Override
//    public UserNotes update(UserNotes userNotes, String name)
//    {
//        Authentication authentication = SecurityContextHolder.getContext()
//                .getAuthentication();
////        UserNotes currentUser = usernotesrepoistory.findAllByUser_Username(authentication.getName());
//
//        if (id == currentUser.getUserid())
//        {
////            if (user.getUsername() != null)
////            {
////                currentUser.setUsernotes(userNotes.getUsernotes());
////            }
//
//
//            if (userNotes.getUsernotes(name))
//
//            {
//                for (UserNotes ue : user.getUserNotes())
//                {
//                    currentUser.getUserNotes()
//                            .add(new UserNotes(currentUser, ue.getUsernotes()));
//                }
//            }
//
//            return userrepos.save(currentUser);
//        } else
//        {
//            throw new ResourceNotFoundException(id + " Not current user");
//        }
//    }
}
