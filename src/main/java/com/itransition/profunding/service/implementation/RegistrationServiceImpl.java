package com.itransition.profunding.service.implementation;

import com.itransition.profunding.exception.*;
import com.itransition.profunding.exception.registration.*;
import com.itransition.profunding.model.db.RegistrationData;
import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.*;
import com.itransition.profunding.repository.RegistrationDataRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.RegistrationService;
import com.itransition.profunding.service.transformer.RegistrationDataTransformer;
import com.itransition.profunding.service.transformer.RegistrationUserTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * @author v.tarasevich
 * @version 1.0
 * @since 10.09.2017 16:43
 */
@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final JavaMailSender javaMailSender;

    private final UserRepository userRepository;
    private final RegistrationDataRepository registrationDataRepository;
    private final RegistrationDataTransformer registrationDataTransformer;
    private final RegistrationUserTransformer registrationUserTransformer;

    @Override
    public RegistrationResponseDto register(RegistrationRequestDto registrationRequest) {
        checkExisting(registrationRequest);
        RegistrationData registrationData = registrationDataTransformer.makeEntity(registrationRequest);
        Object check = registrationDataRepository.save(registrationData);
        if(check != null) {
            sendConfirmationMessage(registrationData.getEmail(), registrationData.getRegistrationHash());
            return new RegistrationResponseDto(RegistrationResponseStatus.OK.name());
        } else {
            throw new RegistrationDataSavingException("Saving of registration data was unsuccessful.");
        }
    }

    @Override
    public void confirm(String confirmationHash) {
        RegistrationData registrationData = registrationDataRepository.findByRegistrationHash(confirmationHash);
        if (registrationData == null) {
            throw new RegistrationDataNotFoundException("Registration data not found.");
        }
        User registeredUser = registrationUserTransformer.makeEntity(registrationData);
        registeredUser = userRepository.save(registeredUser);
        if(registeredUser == null) {
            throw new NewUserCreatingException("Error in saving data about new user.");
        } else {
            registrationDataRepository.deleteByUsername(registrationData.getUsername());
        }
    }

    @Override
    public void clearExpiredRegistrationData() {
        registrationDataRepository.deleteByExpirationTimeLessThan(System.currentTimeMillis());
    }

    private void checkExisting(RegistrationRequestDto registrationRequestDto) {
        checkUsernameExist(registrationRequestDto.getUsername());
        checkEmailExist(registrationRequestDto.getEmail());
    }

    private void checkUsernameExist(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user != null) {
            throw new UsernameAlreadyExistException();
        }
        RegistrationData registrationData = registrationDataRepository.findByUsername(username);
        if (registrationData != null) {
            throw new UsernameAlreadyExistException();
        }
    }

    private void checkEmailExist(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            throw new EmailAlreadyExistException();
        }
        RegistrationData registrationData = registrationDataRepository.findByEmail(email);
        if(registrationData != null) {
            throw new EmailAlreadyExistException();
        }
    }

    private void sendConfirmationMessage(String email, String registrationHash) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(email);
            helper.setSubject("Confirm registration.");
            helper.setText("To confirm registration go to: http://localhost:8080/registration/" + registrationHash);
            sendEmail(mail, email);
        } catch (MessagingException e) {
            registrationDataRepository.deleteByEmail(email);
            throw new EmailSendingException("Error in sending confirmation message.\n" +
                                            "Registration data deleted.\nRecipient : " + email);
        }
    }

    private void sendEmail(MimeMessage mail, String emailAddress) {
        try {
            javaMailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
            registrationDataRepository.deleteByEmail(emailAddress);
            throw new EmailSendingException("Cannot send mail.\n" +
                                            "Registration data deleted.\nRecipient : " + emailAddress);
        }
    }
}
