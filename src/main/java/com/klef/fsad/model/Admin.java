package com.klef.fsad.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_table")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	private int id;
	@Column(name = "admin_name", length = 50)
	private String name;
	@Column(name = "admin_username", length = 30, unique = true)
	private String username;
	@Column(name = "admin_password", nullable = false)
	private String password;
	@Column(nullable = false)
	private String email;

	private String otp;
	private Long otpExpiryTime;
	private boolean twoFactorEnabled;
	private String lastLogin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Long getOtpExpiryTime() {
		return otpExpiryTime;
	}

	public void setOtpExpiryTime(Long otpExpiryTime) {
		this.otpExpiryTime = otpExpiryTime;
	}

	public boolean isTwoFactorEnabled() {
		return twoFactorEnabled;
	}

	public void setTwoFactorEnabled(boolean twoFactorEnabled) {
		this.twoFactorEnabled = twoFactorEnabled;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", email="
				+ email + ", otp=" + otp + ", otpExpiryTime=" + otpExpiryTime + ", twoFactorEnabled=" + twoFactorEnabled
				+ ", lastLogin=" + lastLogin + "]";
	}
}