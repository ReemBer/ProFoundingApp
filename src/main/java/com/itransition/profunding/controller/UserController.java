package com.itransition.profunding.controller;

import com.itransition.profunding.model.dto.ProfileDto;
import com.itransition.profunding.model.dto.UserImageOnlyDto;
import com.itransition.profunding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public ProfileDto getProfile(@PathVariable Long userId) {
        ProfileDto dto =  userService.getProfile(userId);
        return dto;
    }

    @PostMapping(value = "/users/update")
    public Boolean updateUserImage(@RequestBody UserImageOnlyDto userImageOnlyDto) {
        return userService.updateUser(userImageOnlyDto);
    }

}
