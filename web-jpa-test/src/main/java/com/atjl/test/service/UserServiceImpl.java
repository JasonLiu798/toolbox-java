package com.atjl.test.service;


import com.atjl.test.domain.User;
import com.atjl.test.repositories.UserJpaRepository;
import com.atjl.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    public List<User> findByName(String name) {
        List<User> userList1 = userRepository.findByName1(name);
        List<User> userList2 = userRepository.findByName2(name);
        List<User> userList3 = userRepository.findByNameAndAddress(name, "3");
        System.out.println("userList1:" + userList1);
        System.out.println("userList2:" + userList2);
        System.out.println("userList3:" + userList3);
        return userRepository.findByName(name);
    }

    public void saveUser(User book) {
        userJpaRepository.save(book);
    }

    @Cacheable("users")
    public User findOne(long id) {
        System.out.println("Cached Pages");
//        Example example = new User
        return null;//userJpaRepository.findOne(id);
    }

    @Override
    public void delete(long id) {
        User u = new User();
        u.setId(id);
        userJpaRepository.delete(u);
    }

}
