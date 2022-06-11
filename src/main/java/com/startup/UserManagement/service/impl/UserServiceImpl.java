package com.startup.UserManagement.service.impl;

import com.startup.UserManagement.dto.RegisterRequest;
import com.startup.UserManagement.entity.Role;
import com.startup.UserManagement.entity.User;
import com.startup.UserManagement.entity.UserDetail;
import com.startup.UserManagement.enums.UserStatus;
import com.startup.UserManagement.service.UserSaveService;
import com.startup.UserManagement.service.UserService;
import com.startup.UserManagement.service.UserValidationService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserValidationService userValidationService;

    private final PasswordEncoder passwordEncoder;

    private final UserSaveService userSaveService;

    @Override
    public String registerUser(RegisterRequest registerRequest) {

        userValidationService.isEmailExists(registerRequest.getEmail());
        Role role=userValidationService.isRoleExists(registerRequest.getUserRole());
        User user=new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        String username=registerRequest.getEmail().substring(0,registerRequest.getEmail().indexOf("@"));
        user.setUsername(username);
        user.setStatus(UserStatus.ACTIVE);
        user.setRole(role);

        UserDetail userDetail=new UserDetail();
        userDetail.setName(registerRequest.getName());
        userDetail.setGender(registerRequest.getGender());
        userDetail.setPhone(registerRequest.getPhone());
        userDetail.setStartDate(new Date());
        userDetail.setEndDate(new Date());

        User user1=userSaveService.saveUser(user,userDetail);

        return user1.getUsername();
    }
}
