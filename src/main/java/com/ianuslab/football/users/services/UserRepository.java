package com.ianuslab.football.users.services;

import com.ianuslab.football.users.model.UserDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDao, Integer> {
    Optional<UserDao> findByEmail(String email);
}
