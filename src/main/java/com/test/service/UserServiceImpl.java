package com.test.service;

import com.test.model.User;
import com.test.model.enums.Status;
import com.test.repository.UserRepository;
import com.test.util.exception.DuplicateException;
import com.test.util.exception.NotFoundException;
import com.test.util.exception.TimeOutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private RegistrationHelperService registrationHelperService;
    @Autowired
    private VerificationCodeTimeService verificationCodeTimeService;

    @Override
    public User getById(int id) throws NotFoundException {
        User user = userRepository.getById(id);
        if (user == null) {
            throw new NotFoundException("could not found user whit current id " + id);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void save(User user) throws DuplicateException {
        if (userRepository.getByEmail(user.getEmail()) != null) {
            throw new DuplicateException("Duplicate Email");
        }
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        Date date = new Date();
        user.setDate(date);
        String fCode = registrationHelperService.randomAlphaNumeric(8);
        if (userRepository.getByVerificationCode(fCode) != null) {
            throw new DuplicateException("Duplicate Verification Code");
        }
        user.setVerificationCode(fCode);
        user.setStatus(Status.UNVERIFIED);
        String email = user.getEmail();
        emailSenderService.sendEmail(email, fCode, "Here are you're verification code");
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void verifyUser(String code) throws NotFoundException, TimeOutException {
        User user = userRepository.getByVerificationCode(code);
        if (user == null) {
            throw new NotFoundException("Wrong verification code");
        }
        if (!verificationCodeTimeService.dateOfVerificationCode(user)) {
            throw new TimeOutException("Time Out");
        }
        user.setStatus(Status.ACTIVE);
        user.setVerificationCode(null);
        userRepository.save(user);
    }

    @Transactional


    @Override
    public User getByEmail(String email) {
        User user = userRepository.getByEmail(email);
        return user;
    }

    @Override
    public User getByVerificationCode(String verificationCode) {
        User user = userRepository.getByVerificationCode(verificationCode);
        return user;
    }

    @Transactional
    @Override
    public void resetPassword(String email) throws NotFoundException {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new NotFoundException("User with this email is not exists");
        }
        String token = registrationHelperService.randomAlphaNumeric(15);
        user.setResetPasswordToken(token);
        emailSenderService.sendEmail(email, token, "This is your password reset token");
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void changePassword(String token, String password) throws NotFoundException {
        User user = userRepository.getByResetPasswordToken(token);
        if (user == null) {
            throw new NotFoundException("User with this token is not exists");
        }
        if (user.getResetPasswordToken().equals(token)) {
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setResetPasswordToken(null);
            userRepository.save(user);
        } else {
            throw new NotFoundException("User with this token does not exist");
        }
    }

    @Transactional
    @Override
    public User getByResetPasswordToken(String token) {
        User user = userRepository.getByResetPasswordToken(token);
        return user;
    }
}
