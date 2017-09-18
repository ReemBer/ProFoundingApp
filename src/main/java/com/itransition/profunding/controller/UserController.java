package com.itransition.profunding.controller;

import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.UserDto;
import com.itransition.profunding.service.AuthenticationService;
import com.itransition.profunding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 16.09.2017 22:56
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{userId}")
    public UserDto getProfile(@PathVariable Long userId) {
        UserDto dto =  userService.getUser(userId);
        System.out.println(dto);
        return dto;
    }
}
