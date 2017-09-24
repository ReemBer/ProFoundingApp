package com.itransition.profunding.service;

import com.itransition.profunding.model.dto.user.AdminPageUserDto;

import java.util.List;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 24.09.2017 20:19
 */
public interface AdminService {
    List<AdminPageUserDto> getUsers();
    boolean setBlockingStatus(List<AdminPageUserDto> users, boolean block);
    boolean setAsConfirmed(Long userId);
    boolean deleteUsers(List<AdminPageUserDto> users);
}
