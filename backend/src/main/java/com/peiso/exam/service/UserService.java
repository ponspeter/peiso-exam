package com.peiso.exam.service;

import com.peiso.exam.common.model.ListResponse;
import com.peiso.exam.model.User;

public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    boolean deleteUserById(Long id);

    User getUserById(Long id);

    ListResponse listUsers();

}
