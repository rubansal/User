package com.startup.UserManagement.service.impl;

import com.startup.UserManagement.dto.RegisterRequest;
import com.startup.UserManagement.entity.User;
import com.startup.UserManagement.entity.UserDetail;
import com.startup.UserManagement.repository.UserDetailRepository;
import com.startup.UserManagement.repository.UserRepository;
import com.startup.UserManagement.service.UserSaveService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class UserSaveServiceImpl implements UserSaveService {

    private final UserRepository userRepository;

    private final UserDetailRepository userDetailRepository;

    @Override
    @Transactional
    public User saveUser(User user, UserDetail userDetail) {

        User user1=userRepository.save(user);
        userDetail.setUser(user1);
        userDetailRepository.save(userDetail);
        return user1;
    }
}
