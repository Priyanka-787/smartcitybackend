package com.klef.fsad.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.model.User;
import com.klef.fsad.repository.InfrastructureRepository;
import com.klef.fsad.repository.IssueRepository;
import com.klef.fsad.repository.ServiceEntityRepository;
import com.klef.fsad.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private InfrastructureRepository infrastructureRepository;
	@Autowired
	private ServiceEntityRepository serviceRepository;
	@Autowired
	private IssueRepository issueRepository;

	@Override
	public String registerUser(User user) {

		user.setAccountStatus("ACTIVE");
		user.setTwoFactorEnabled(false);
		user.setNotificationsEnabled(true);
		user.setCreatedAt(LocalDateTime.now());

		userRepository.save(user);

		emailService.sendHtmlEmail(user.getEmail(), "Welcome to Smart City", "Welcome " + user.getName(), "SmartCity");

		return "User Registered Successfully";
	}

	@Override
	public User checkUserLogin(String email, String password) {

		User user = userRepository.findByEmail(email);

		if (user != null && user.getPassword().equals(password)) {
			user.setLastLogin(LocalDateTime.now().toString());
			userRepository.save(user);
		}

		return user;
	}

	@Override
	public List<User> viewAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User viewUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public String updateProfile(int id, String name, String email, String gender, String contact, String location) {

		User user = userRepository.findById(id).orElse(null);

		if (user == null)
			return "User Not Found";

		user.setName(name);
		user.setEmail(email);
		user.setGender(gender);
		user.setContact(contact);
		user.setLocation(location);

		userRepository.save(user);

		return "Profile Updated";
	}

	@Override
	public String changePassword(int id, String oldPassword, String newPassword) {

		User user = userRepository.findById(id).orElse(null);

		if (user == null)
			return "User Not Found";

		if (!user.getPassword().equals(oldPassword)) {
			return "Old Password Incorrect";
		}

		user.setPassword(newPassword);
		userRepository.save(user);

		return "Password Changed";
	}

	@Override
	public String toggle2FA(int id, boolean enabled) {

		User user = userRepository.findById(id).orElse(null);

		if (user == null)
			return "User Not Found";

		user.setTwoFactorEnabled(enabled);
		userRepository.save(user);

		return "2FA Updated";
	}

	@Override
	public String updatePreferences(int id, boolean notifications) {

		User user = userRepository.findById(id).orElse(null);

		if (user == null)
			return "User Not Found";

		user.setNotificationsEnabled(notifications);
		userRepository.save(user);

		return "Preferences Updated";
	}

	@Override
	public Map<String, Object> getDashboardStats() {

		Map<String, Object> res = new HashMap<>();

		try {
			res.put("hospitals", infrastructureRepository.countByType("HOSPITAL"));
			res.put("parks", infrastructureRepository.countByType("PARK"));
			res.put("busRoutes", serviceRepository.countByType("BUS"));
			res.put("openIssues", issueRepository.countByStatusNot("RESOLVED"));
		} catch (Exception e) {
			e.printStackTrace();
			res.put("hospitals", 0);
			res.put("parks", 0);
			res.put("busRoutes", 0);
			res.put("openIssues", 0);
		}
		return res;
	}
}