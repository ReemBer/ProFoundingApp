package com.itransition.profunding.controller;

import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.UserDto;
import com.itransition.profunding.service.AuthenticationService;
import com.itransition.profunding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 16.09.2017 22:56
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/profile/{userId}")
    public UserDto getProfile(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
}
