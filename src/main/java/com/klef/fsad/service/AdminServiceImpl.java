package com.klef.fsad.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.model.Admin;
import com.klef.fsad.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
	public Admin checkAdminLogin(String email, String password) {

		Admin admin = adminRepository.findByEmail(email);

		if (admin != null && admin.getPassword().equals(password)) {
			admin.setLastLogin(LocalDateTime.now().toString());
			adminRepository.save(admin);
		}

		return admin;
	}

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public String updateProfile(int id, String name, String email) {

        Admin admin = adminRepository.findById(id).orElse(null);

        if (admin == null) return "Admin Not Found";

        admin.setName(name);
        admin.setEmail(email);

        adminRepository.save(admin);

        return "Admin Profile Updated";
    }

    @Override
    public String changePassword(int id, String oldPassword, String newPassword) {

        Admin admin = adminRepository.findById(id).orElse(null);

        if (admin == null) return "Admin Not Found";

        if (!admin.getPassword().equals(oldPassword)) {
            return "Old Password Incorrect";
        }

        admin.setPassword(newPassword);
        adminRepository.save(admin);

        return "Password Changed Successfully";
    }

    @Override
    public String toggle2FA(int id, boolean enabled) {

        Admin admin = adminRepository.findById(id).orElse(null);

        if (admin == null) return "Admin Not Found";

        admin.setTwoFactorEnabled(enabled);
        adminRepository.save(admin);

        return "2FA Updated";
    }

}