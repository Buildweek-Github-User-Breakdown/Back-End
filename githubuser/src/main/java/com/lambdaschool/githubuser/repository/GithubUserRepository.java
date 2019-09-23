package com.lambdaschool.githubuser.repository;

import com.lambdaschool.githubuser.models.GithubUser;
import org.springframework.data.repository.CrudRepository;

public interface GithubUserRepository extends CrudRepository<GithubUser, Long> {
    GithubUser findByLogin(String login);
}
