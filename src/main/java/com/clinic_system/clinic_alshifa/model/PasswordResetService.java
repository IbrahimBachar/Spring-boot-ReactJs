package com.clinic_system.clinic_alshifa.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private MyAppUserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Generate reset token and save it
    public String createPasswordResetToken(String email) {
        Optional<MyAppUser> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }

        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setEmail(email);
        resetToken.setExpirationDate(new Date(System.currentTimeMillis() + 15 * 60 * 1000)); // 15 minutes validity

        tokenRepository.save(resetToken);
        return token;
    }

    // Validate reset token and reset password
    public void resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> resetToken = tokenRepository.findByToken(token);
        if (!resetToken.isPresent() || resetToken.get().getExpirationDate().before(new Date())) {
            throw new IllegalArgumentException("Invalid or expired token");
        }

        MyAppUser user = userRepository.findByEmail(resetToken.get().getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // Remove token after successful reset
        tokenRepository.delete(resetToken.get());
    }
}
