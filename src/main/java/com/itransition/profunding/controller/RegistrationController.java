package com.itransition.profunding.controller;

import com.itransition.profunding.model.dto.ConfirmRegistrationResponseStatus;
import com.itransition.profunding.model.dto.RegistrationRequestDto;
import com.itransition.profunding.model.dto.RegistrationResponseDto;
import com.itransition.profunding.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        ConfirmRegistrationResponseStatus response = registrationService.confirm(registrationHash);
        return "redirect:http://localhost:8081/login?status=" + response.name();
    }
}
