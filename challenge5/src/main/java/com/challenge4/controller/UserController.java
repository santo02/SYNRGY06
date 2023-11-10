package com.challenge4.controller;

import com.challenge4.model.merchants;
import com.challenge4.model.products;
import com.challenge4.model.users;
import com.challenge4.repository.UserRepository;
import com.challenge4.response.ResponseHandler;
import com.challenge4.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private final static Logger logger =  LoggerFactory.getLogger(MerchantController.class);

    @PostMapping
    public users add(@RequestBody users user){
        return userService.create(user);
    }

    @GetMapping
    public List<users> getAll(){
        return userService.getAll();
    }

    @GetMapping("{id}")
    public users show(@PathVariable("id") UUID idUser){
        return userService.getById(idUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID idUser){
        try {
            users deleteuser = userService.delete(idUser);

            if (deleteuser != null) {
                return  ResponseHandler.generateResponse("Data berhasil di hapus!", HttpStatus.OK, deleteuser );
            } else {
                return  ResponseHandler.generateResponse("Data Tidak Ditemukan", HttpStatus.NOT_FOUND, null);
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
    @PatchMapping("{userID}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID userID,
                                                @RequestBody users updatedUser) {
        users userResponseUpdate = userService.updateUSer(userID, updatedUser);
        return ResponseHandler.generateResponse("USer updated successfully", HttpStatus.OK, userResponseUpdate);
    }
}
