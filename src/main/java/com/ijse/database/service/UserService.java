package com.ijse.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.database.dto.UserPwdDTO;
import com.ijse.database.entity.User;

@Service
public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long id);
    User changeUserPassword(Long id, UserPwdDTO userPwdDTO);
}
