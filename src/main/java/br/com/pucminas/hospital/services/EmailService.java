package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.model.dto.EmailDto;
import br.com.pucminas.hospital.model.dto.UserDTO;
import br.com.pucminas.hospital.model.entity.Email;
import br.com.pucminas.hospital.model.enums.StatusEmail;
import br.com.pucminas.hospital.repository.EmailRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {


    @Value("${spring.mail.username}")
    public String emailFrom;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserService userService;

    @Autowired
    UserTokenService userTokenService;

    public EmailDto sendPasswordRecoveryEmail(String username) {
        UserDTO userDto = userService.getUserByUsername(username);
        String subject = "[HSFA] Change userDto password";
        String recoveryToken = DigestUtils.sha256Hex(userDto.getUserName() + userDto.getEmail() + LocalDateTime.now());
        userService.setRecoveryToken(recoveryToken, username);
        String text = "recovery link: https://api-busca-fonada.herokuapp.com/change-password/recoveryToken?=" + recoveryToken;
        EmailDto emailDto = new EmailDto(username, this.emailFrom, userDto.getEmail(), subject, text);
        Email email = new Email(emailDto);
        this.sendEmail(email);
        return emailDto;
    }

    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(email);
        }


    }

}
