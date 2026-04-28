package com.klef.fsad.service;

import java.util.List;
import java.util.Map;

import com.klef.fsad.model.User;

public interface UserService {

    String registerUser(User user);
    User checkUserLogin(String email, String password);
    List<User> viewAllUsers();
    User viewUserById(int id);
    User getUserByEmail(String email);
    String updateProfile(int id, String name, String email, String gender, String contact, String location);
    String changePassword(int id, String oldPassword, String newPassword);
    String toggle2FA(int id, boolean enabled);
    String updatePreferences(int id, boolean notifications);
    Map<String, Object> getDashboardStats();

}