package com.itransition.profunding.controller;

import com.itransition.profunding.model.dto.user.AdminPageUserDto;
import com.itransition.profunding.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 24.09.2017 18:55
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public List<AdminPageUserDto> getUsers() {
        return adminService.getUsers();
    }

    @PostMapping(value = "/confirm")
    public Boolean setAsConfirmed(@RequestBody Long userId) {
        return adminService.setAsConfirmed(userId);
    }

    @PostMapping(value = "/block")
    public Boolean blockUsers(@RequestBody List<AdminPageUserDto> users) {
        return adminService.setBlockingStatus(users, true);
    }

    @PostMapping(value = "/unblock")
    public Boolean unblockUsers(@RequestBody List<AdminPageUserDto> users) {
        return adminService.setBlockingStatus(users, false);
    }

    @PostMapping(value = "/delete")
    public Boolean deleteUsers(@RequestBody List<AdminPageUserDto> users) {
        return adminService.deleteUsers(users);
    }
}
