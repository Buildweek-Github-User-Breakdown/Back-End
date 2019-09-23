package com.lambdaschool.githubuser.repository;

import com.lambdaschool.githubuser.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
