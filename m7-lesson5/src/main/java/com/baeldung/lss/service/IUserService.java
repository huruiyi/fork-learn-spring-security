package com.baeldung.lss.service;

import com.baeldung.lss.model.User;
import com.baeldung.lss.validation.EmailExistsException;

public interface IUserService {

    User findUserByEmail(String email);

    User registerNewUser(User user) throws EmailExistsException;

    User updateExistingUser(User user) throws EmailExistsException;
}
