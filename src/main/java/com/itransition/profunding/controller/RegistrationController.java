package com.itransition.profunding.controller;

import com.itransition.profunding.exception.*;
import com.itransition.profunding.exception.registration.*;
import com.itransition.profunding.model.dto.*;
import com.itransition.profunding.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 09.09.2017 17:48
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/registration")
@RequiredArgsConstructor
public class RegistrationController {


    private final RegistrationService registrationService;

    @PostMapping
    public @ResponseBody RegistrationResponseDto register(@RequestBody RegistrationRequestDto registrationRequestDto){
        registrationService.clearExpiredRegistrationData();
        return registrationService.register(registrationRequestDto);
    }

    @GetMapping(value = "/{registrationHash}")
    public String confirm(@PathVariable String registrationHash) {
        registrationService.confirm(registrationHash);
        return "redirect:http://localhost:8081/login?confirmStatus=OK";
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({UsernameAlreadyExistException.class, EmailAlreadyExistException.class})
    public @ResponseBody RegistrationErrorInfoDto suchUserAlreadyExist(HttpServletRequest request, Exception exception) {
        return new RegistrationErrorInfoDto(request.getRequestURL().toString(),
                                            exception, RegistrationResponseStatus.SUCH_USER_ALREADY_EXIST);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RegistrationDataSavingException.class})
    public @ResponseBody RegistrationErrorInfoDto badSavingData(HttpServletRequest request, Exception exception) {
        return new RegistrationErrorInfoDto(request.getRequestURL().toString(),
                                            exception, RegistrationResponseStatus.SENDING_EMAIL_ERROR);
    }

    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler({EmailSendingException.class})
    public @ResponseBody RegistrationErrorInfoDto badEmailSending(HttpServletRequest request, Exception exception) {
        return new RegistrationErrorInfoDto(request.getRequestURL().toString(),
                                            exception, RegistrationResponseStatus.SENDING_EMAIL_ERROR);
    }

    @ResponseStatus(value = HttpStatus.GONE)
    @ExceptionHandler({RegistrationDataNotFoundException.class})
    public @ResponseBody RegistrationErrorInfoDto registrationDataNotFound(HttpServletRequest request, Exception exception) {
        return new RegistrationErrorInfoDto(request.getRequestURL().toString(),
                                            exception, RegistrationResponseStatus.REGISTRATION_DATA_NOT_FOUND);
    }

    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler({NewUserCreatingException.class})
    public @ResponseBody RegistrationErrorInfoDto badUserCreating(HttpServletRequest request, Exception exception) {
        return new RegistrationErrorInfoDto(request.getRequestURL().toString(),
                                            exception, RegistrationResponseStatus.USER_CREATING_ERROR);
    }
}
