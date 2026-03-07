package com.command.toyvillage_server.domain.auth.service;

import com.command.toyvillage_server.domain.auth.domain.EmailVerification;
import com.command.toyvillage_server.domain.auth.domain.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPasswordResetService {
    private final EmailVerificationRepository emailVerificationRepository;
    private final JavaMailSender mailSender;

    public void execute(String email){
        String code = generateCode();

        EmailVerification verification = EmailVerification.create(email, code);
        emailVerificationRepository.save(verification);

        sendEmail(email, code);
    }

    private String generateCode() {
        return String.format("%06d", new java.security.SecureRandom().nextInt(1000000));
    }

    private void sendEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[itda] 비밀번호 재설정 인증 코드 : " + code);
        message.setText("인증 코드: " + code + "\n\n5분 이내에 입력해주세요.");
        mailSender.send(message);
    }
}
