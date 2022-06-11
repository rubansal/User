package com.startup.UserManagement.service;

import com.startup.UserManagement.dto.RegisterRequest;

public interface UserService {

    String registerUser(RegisterRequest registerRequest);
}
