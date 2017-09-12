package com.itransition.profunding.service.implementation;

import com.itransition.profunding.exception.EmailAlreadyExistException;
import com.itransition.profunding.exception.UsernameAlreadyExistException;
import com.itransition.profunding.model.db.RegistrationData;
import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.*;
import com.itransition.profunding.repository.RegistrationDataRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.RegistrationService;
import com.itransition.profunding.service.Transformer;
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
    private final Transformer<RegistrationData, RegistrationRequestDto> registrationDataTransformer;
    private final Transformer<User, RegistrationData> registrationUserTransformer;

    @Override
    public RegistrationResponseDto register(RegistrationRequestDto registrationRequest) {
        try {
            checkExisting(registrationRequest);
            RegistrationData registrationData = registrationDataTransformer.makeEntity(registrationRequest);
            Object check = registrationDataRepository.save(registrationData);
            if(check != null) {
                sendConfirmationMessage(registrationData.getEmail(), registrationData.getRegistrationHash());
                return new RegistrationResponseDto(RegistrationResponseStatus.OK.name());
            } else throw new Exception();
        } catch (UsernameAlreadyExistException e) {
            return new RegistrationResponseDto(RegistrationResponseStatus.SUCH_USERNAME_EXIST.name());
        } catch (EmailAlreadyExistException e) {
            return new RegistrationResponseDto(RegistrationResponseStatus.SUCH_MAIL_EXIST.name());
        } catch (Exception e) {
            return new RegistrationResponseDto(RegistrationResponseStatus.ERROR.name());
        }
    }

    @Override
    public ConfirmRegistrationResponseStatus confirm(String confirmationHash) {
        RegistrationData registrationData = registrationDataRepository.findByRegistrationHash(confirmationHash);
        if (registrationData == null) {
            return ConfirmRegistrationResponseStatus.REGISTRATION_DATA_NOT_FOUND;
        }
        User registeredUser = registrationUserTransformer.makeEntity(registrationData);
        registeredUser = userRepository.save(registeredUser);
        if(registeredUser == null) {
            return ConfirmRegistrationResponseStatus.ERROR;
        } else {
            return ConfirmRegistrationResponseStatus.OK;
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

    private void sendConfirmationMessage(String email, String registrationHash) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo("vlad.tarasevich.97@mail.ru");
        helper.setSubject("Its a subject.");
        helper.setText("To confirm registration go to: http://localhost:8080/registration/" + registrationHash);
        javaMailSender.send(mail);
    }
}
