package com.klef.fsad.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	@Column(name = "user_name", nullable = false, length = 50)
	private String name;
	@Column(name = "user_gender", nullable = false, length = 10)
	private String gender;
	@Column(name = "user_email", nullable = false, unique = true, length = 50)
	private String email;
	@Column(name = "user_password", nullable = false)
	private String password;
	@Column(name = "user_contact", nullable = false, length = 15)
	private String contact;
	@Column(name = "user_location", nullable = false, length = 100)
	private String location;
	@Column(name = "account_status", nullable = false)
	private String accountStatus; // ACTIVE / BLOCKED / INACTIVE
	@Column
	private String otp;
	@Column
	private Long otpExpiryTime;
	@Column
	private boolean twoFactorEnabled;
	@Column
	private boolean notificationsEnabled;
	@Column
	private String lastLogin;
	@Column
	private LocalDateTime createdAt;
	// User → Issues
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Issue> issues;
	// User → Feedback
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Feedback> feedbacks;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
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

	public boolean isNotificationsEnabled() {
		return notificationsEnabled;
	}

	public void setNotificationsEnabled(boolean notificationsEnabled) {
		this.notificationsEnabled = notificationsEnabled;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", gender=" + gender + ", email=" + email + ", password="
				+ password + ", contact=" + contact + ", location=" + location + ", accountStatus=" + accountStatus
				+ ", otp=" + otp + ", otpExpiryTime=" + otpExpiryTime + ", twoFactorEnabled=" + twoFactorEnabled
				+ ", notificationsEnabled=" + notificationsEnabled + ", lastLogin=" + lastLogin + ", createdAt="
				+ createdAt + ", issues=" + issues + ", feedbacks=" + feedbacks + "]";
	}

}