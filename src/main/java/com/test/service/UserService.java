package com.test.service;

import com.test.model.User;
import com.test.util.exception.DuplicateException;
import com.test.util.exception.NotFoundException;
import com.test.util.exception.TimeOutException;

import java.util.List;

public interface UserService {
    User getById(int id) throws NotFoundException;

    User getByEmail(String email);

    User getByResetPasswordToken(String token);

    User getByVerificationCode(String verificationCode);

    List<User> getAll();

    void deleteById(int id);

    void save(User user) throws DuplicateException;

    void verifyUser(String code) throws NotFoundException, TimeOutException;

    void resetPassword(String email) throws NotFoundException;

    void changePassword(String token, String password) throws NotFoundException;


}
