package com.startup.UserManagement.service.impl;

import com.startup.UserManagement.entity.Role;
import com.startup.UserManagement.entity.User;
import com.startup.UserManagement.enums.UserRole;
import com.startup.UserManagement.exception.BadRequestException;
import com.startup.UserManagement.repository.RoleRepository;
import com.startup.UserManagement.repository.UserRepository;
import com.startup.UserManagement.service.UserValidationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Slf4j
public class UserValidationServiceImpl implements UserValidationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public void isEmailExists(String email) {

        User user=userRepository.findByEmail(email);
        log.info("user: {}", user);

        if(user!=null){
            throw new BadRequestException("Email already exists");
        }
    }

    @Override
    public Role isRoleExists(UserRole userRole) {

        Role role=roleRepository.findByName(userRole.name());
        if(role==null){
            throw new BadRequestException("Role does not exist");
        }
        return role;
    }
}
