package com.startup.UserManagement.service;

import com.startup.UserManagement.entity.Role;
import com.startup.UserManagement.enums.UserRole;

public interface UserValidationService {

    void isEmailExists(String email);

    Role isRoleExists(UserRole userRole);
}
