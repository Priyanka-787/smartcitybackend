package com.klef.fsad.service;

import com.klef.fsad.model.Admin;

public interface AdminService {

	Admin checkAdminLogin(String username, String password);
	Admin getAdminByEmail(String email);
	String updateProfile(int id, String name, String email);
	String changePassword(int id, String oldPassword, String newPassword);
	String toggle2FA(int id, boolean enabled);
}