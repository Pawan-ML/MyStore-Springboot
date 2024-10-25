package com.ijse.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.dto.UserPwdDTO;
import com.ijse.database.entity.User;
import com.ijse.database.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User changeUserPassword(Long id, UserPwdDTO userPwdDTO) {
        User user = userRepository.findById(id).orElse(null);

        System.out.println(userPwdDTO.getPassword()); //debugging

        if(user != null) {
            user.setPassword(userPwdDTO.getPassword());
            return userRepository.save(user);
        } else {
            return null;
        }
    }

}
