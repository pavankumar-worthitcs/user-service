package com.wcs.user_service.controller;

import com.wcs.user_service.Entity.Product;
import com.wcs.user_service.Entity.User;
import com.wcs.user_service.Entity.UserDTORequest;
import com.wcs.user_service.Entity.UserDTOResponse;
import com.wcs.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StringReader;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/saveUserProduct")
    public ResponseEntity<Object> saveUserProduct(@RequestParam Long userId,@RequestBody Product product){
        return userService.saveUserProduct(userId,product);
    }

    @GetMapping("/fetchUserProductById")
    public ResponseEntity<Object> fetchUserProductById(@RequestParam Long userId,@RequestParam Long productId){
        return userService.fetchUserProductById(userId,productId);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDTOResponse> updateUser(@RequestBody UserDTORequest userDTOReq){

        return userService.updateUser(userDTOReq);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(){
        return userService.deleteUser();
    }

}
