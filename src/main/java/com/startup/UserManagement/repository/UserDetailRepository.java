package com.startup.UserManagement.repository;

import com.startup.UserManagement.entity.UserDetail;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailRepository extends CrudRepository<UserDetail, Integer> {
}
