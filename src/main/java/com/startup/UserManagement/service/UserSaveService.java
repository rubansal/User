package com.startup.UserManagement.service;

import com.startup.UserManagement.dto.RegisterRequest;
import com.startup.UserManagement.entity.User;
import com.startup.UserManagement.entity.UserDetail;

public interface UserSaveService {

    User saveUser(User user, UserDetail userDetail);
}
