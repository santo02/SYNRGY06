package com.challenge4.service;

import com.challenge4.model.merchants;
import com.challenge4.model.users;
import com.challenge4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<users> getAll(){
        return userRepository.findAll();
    }

    public users create(users user){
        return userRepository.save(user);
    }

}
