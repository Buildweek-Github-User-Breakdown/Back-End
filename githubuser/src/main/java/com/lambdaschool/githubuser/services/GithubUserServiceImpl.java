package com.lambdaschool.githubuser.services;

import com.lambdaschool.githubuser.exceptions.ResourceNotFoundException;
import com.lambdaschool.githubuser.models.GithubUser;
import com.lambdaschool.githubuser.repository.GithubUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GithubUserServiceImpl implements GithubUserService {

    @Autowired
    GithubUserRepository githubuserrepo;

    @Override
    public GithubUser findByName(String name) {
        GithubUser gr = githubuserrepo.findByLogin(name);

        if (gr != null)
        {
            return gr;
        } else
        {
            throw new ResourceNotFoundException(name);
        }
    }
}
