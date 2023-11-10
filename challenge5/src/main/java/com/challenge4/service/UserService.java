package com.challenge4.service;

import com.challenge4.model.merchants;
import com.challenge4.model.products;
import com.challenge4.model.users;
import com.challenge4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public users getById(UUID idUser) {
        Optional<users> usersOptional = userRepository.findById(idUser);
        if(usersOptional.isEmpty()){
            return null;
        }
        return usersOptional.get();

    }

    public users delete(UUID idUser) {
        Optional<users> usersOptional = userRepository.findById(idUser);
        if(usersOptional.isEmpty()){
            return null;
        }
        users deleteUser = usersOptional.get();
        userRepository.deleteById(idUser);
        return deleteUser;
    }

    public users updateUSer(UUID userID, users updatedUser) {
        users existingUser = null;
        try {
            existingUser = userRepository.findById(userID)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        return userRepository.save(existingUser);
    }
}
