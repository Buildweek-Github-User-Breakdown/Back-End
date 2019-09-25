package com.lambdaschool.githubuser.services;

import com.lambdaschool.githubuser.models.GithubUser;

public interface GithubUserService {
   GithubUser findByName(String name);
}
