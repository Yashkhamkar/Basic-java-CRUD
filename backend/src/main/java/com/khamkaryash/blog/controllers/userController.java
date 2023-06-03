package com.khamkaryash.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khamkaryash.blog.excption.UserNotFoundException;
import com.khamkaryash.blog.models.userModel;
import com.khamkaryash.blog.repository.userRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class userController {
	@Autowired
	private userRepository UserRepository;
	@PostMapping("/user")
	userModel newUser(@RequestBody userModel newUser) {
		return UserRepository.save(newUser);
	}
	@GetMapping("/users")
	List<userModel> getall(){
		return UserRepository.findAll();	
	}
    @GetMapping("/user/{id}")
    userModel getUserById(@PathVariable Long id) {
        return UserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    @PutMapping("/user/{id}")
    userModel updateUser(@RequestBody userModel newUser, @PathVariable Long id) {
        return UserRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return UserRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!UserRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        UserRepository.deleteById(id);
        return  "User with id "+id+" has been deleted success.";
    }
}
