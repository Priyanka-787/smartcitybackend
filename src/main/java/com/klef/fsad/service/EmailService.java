package com.klef.fsad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.klef.fsad.model.Admin;
import com.klef.fsad.model.User;
import com.klef.fsad.repository.AdminRepository;
import com.klef.fsad.repository.UserRepository;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;

    private final String FROM_EMAIL = "2200030326cseh@gmail.com";

    public void sendHtmlEmail(String to, String subject, String htmlContent, String senderName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(new InternetAddress(FROM_EMAIL, senderName));
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText("Your verification code is: " + htmlContent.replaceAll("<[^>]*>", ""), htmlContent);

            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateOtp() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    public String sendOtpToUser(String email) {

        User user = userRepository.findByEmail(email);
        if (user == null) return "User not found";

        String otp = generateOtp();

        user.setOtp(otp);
        user.setOtpExpiryTime(System.currentTimeMillis() + 5 * 60 * 1000);
        userRepository.save(user);

        String subject = "Your Smart City Management Verification Code";
        String html = getOtpEmailTemplate(user.getName(), otp);

        sendHtmlEmail(email, subject, html, "Smart City Management Security 🔐");

        return "OTP sent to user email";
    }

    public String verifyOtpUser(String email, String otp) {

        User user = userRepository.findByEmail(email);

        if (user == null) return "User not found";
        if (user.getOtp() == null) return "No OTP requested";
        if (System.currentTimeMillis() > user.getOtpExpiryTime()) return "OTP Expired";
        if (!user.getOtp().equals(otp)) return "Invalid OTP";

        return "OTP Verified";
    }

    public String resetPasswordUser(String email, String newPassword) {

        User user = userRepository.findByEmail(email);

        if (user == null) return "User not found";

        user.setPassword(newPassword);
        user.setOtp(null);
        user.setOtpExpiryTime(null);

        userRepository.save(user);

        return "User password reset successful";
    }

    public String sendOtpToAdmin(String email) {

        Admin admin = adminRepository.findByEmail(email);
        if (admin == null) return "Admin not found";

        String otp = generateOtp();

        admin.setOtp(otp);
        admin.setOtpExpiryTime(System.currentTimeMillis() + 5 * 60 * 1000);
        adminRepository.save(admin);

        String subject = "Your Smart City Management Verification Code";
        String html = getOtpEmailTemplate(admin.getUsername(), otp);

        sendHtmlEmail(email, subject, html, "Smart City Management Admin Security 🔐");

        return "OTP sent to admin email";
    }

    public String verifyOtpAdmin(String email, String otp) {

        Admin admin = adminRepository.findByEmail(email);

        if (admin == null) return "Admin not found";
        if (admin.getOtp() == null) return "No OTP requested";
        if (System.currentTimeMillis() > admin.getOtpExpiryTime()) return "OTP Expired";
        if (!admin.getOtp().equals(otp)) return "Invalid OTP";

        return "OTP Verified";
    }

    public String resetPasswordAdmin(String email, String newPassword) {

        Admin admin = adminRepository.findByEmail(email);

        if (admin == null) return "Admin not found";

        admin.setPassword(newPassword);
        admin.setOtp(null);
        admin.setOtpExpiryTime(null);

        adminRepository.save(admin);

        return "Admin password reset successful";
    }

    public String sendOtpFor2FAUser(String email) {

        User user = userRepository.findByEmail(email);
        if (user == null) return "User not found";

        String otp = generateOtp();

        user.setOtp(otp);
        user.setOtpExpiryTime(System.currentTimeMillis() + 5 * 60 * 1000);
        userRepository.save(user);

        String subject = "2FA Verification OTP 🔐";
        String html = get2FAOtpEmailTemplate(user.getName(), otp);

        sendHtmlEmail(email, subject, html, "Smart City Management 2FA Security 🔐");

        return "OTP sent for 2FA";
    }

    public String verifyOtpFor2FAUser(String email, String otp) {

        User user = userRepository.findByEmail(email);

        if (user == null) return "User not found";
        if (user.getOtp() == null) return "No OTP requested";
        if (System.currentTimeMillis() > user.getOtpExpiryTime()) return "OTP Expired";
        if (!user.getOtp().equals(otp)) return "Invalid OTP";

        user.setTwoFactorEnabled(true);
        user.setOtp(null);
        user.setOtpExpiryTime(null);

        userRepository.save(user);

        return "2FA Enabled Successfully";
    }
    
    public String sendOtpFor2FAAdmin(String email) {

        Admin admin = adminRepository.findByEmail(email);
        if (admin == null) return "Admin not found";

        String otp = generateOtp();

        admin.setOtp(otp);
        admin.setOtpExpiryTime(System.currentTimeMillis() + 5 * 60 * 1000);
        adminRepository.save(admin);

        String subject = "2FA Verification OTP 🔐";
        String html = get2FAOtpEmailTemplate(admin.getName(), otp);

        sendHtmlEmail(email, subject, html, "Smart City Management Admin 2FA 🔐");

        return "OTP sent for 2FA";
    }

    public String verifyOtpFor2FAAdmin(String email, String otp) {

        Admin admin = adminRepository.findByEmail(email);

        if (admin == null) return "Admin not found";
        if (admin.getOtp() == null) return "No OTP requested";
        if (System.currentTimeMillis() > admin.getOtpExpiryTime()) return "OTP Expired";
        if (!admin.getOtp().equals(otp)) return "Invalid OTP";

        admin.setTwoFactorEnabled(true);
        admin.setOtp(null);
        admin.setOtpExpiryTime(null);

        adminRepository.save(admin);

        return "2FA Enabled Successfully";
    }

    public String getWelcomeEmailTemplate(String name, String email) {
        return "<div style='font-family:Arial;background:#0f172a;padding:30px;'>"
                + "<div style='max-width:600px;margin:auto;background:#111827;border-radius:12px;padding:30px;color:white;'>"
                + "<h2 style='color:#22c55e;'>🎉 Welcome to Smart City Management</h2>"
                + "<p>Hi <b>" + name + "</b>,</p>"
                + "<p>Your account has been created successfully.</p>"
                + "<p><b>Email:</b> " + email + "</p>"
                + "<p style='color:#facc15;'>🔐 Password is not shared via email.</p>"
                + "</div></div>";
    }

    public String getOtpEmailTemplate(String name, String otp) {
        return "<div style='font-family:Arial;background:#0f172a;padding:30px;'>"
            + "<div style='max-width:600px;margin:auto;background:#111827;border-radius:12px;padding:30px;color:white;'>"

            + "<h2 style='color:#22c55e;'>Smart City Verification Code</h2>"

            + "<p>Hi <b>" + name + "</b>,</p>"

            + "<p>Your verification code is:</p>"

            + "<div style='font-size:28px;font-weight:bold;letter-spacing:6px;text-align:center;"
            + "background:#1f2937;padding:15px;border-radius:8px;'>"
            + otp +
            "</div>"

            + "<p style='margin-top:15px;'>This code is valid for <b>5 minutes</b>.</p>"

            + "<hr style='margin:20px 0;border-color:#374151;'>"

            + "<p style='font-size:12px;color:#9ca3af;'>"
            + "This email was sent by <b>Smart City Management</b>.<br>"
            + "If you did not request this, please ignore this email."
            + "</p>"

            + "</div></div>";
    }

    public String get2FAOtpEmailTemplate(String name, String otp) {
        return "<div style='font-family:Arial;background:#0f172a;padding:30px;'>"
                + "<div style='max-width:600px;margin:auto;background:#111827;border-radius:12px;padding:30px;color:white;'>"
                + "<h2 style='color:#22c55e;'>🔐 Enable 2FA</h2>"
                + "<p>Hi <b>" + name + "</b>,</p>"
                + "<h1 style='color:#22c55e;text-align:center;'>" + otp + "</h1>"
                + "<p>Valid for 5 minutes</p>"
                + "</div></div>";
    }
}