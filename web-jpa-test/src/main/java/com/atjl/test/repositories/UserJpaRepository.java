package com.atjl.test.repositories;


import com.atjl.test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface UserJpaRepository extends JpaRepository<User,Long> {

}

