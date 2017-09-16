package com.itransition.profunding.model.dto;

import java.util.Date;
import java.util.Set;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 14.09.2017 20:56
 */
public class UserDto {
    private String username;
    private String email;
    private String userRole;
    private String proofingStatus;
    private String userStatus;
    private Date   lastLoginDate;
    private Set<ProjectDto> projectSubscribes;
    private Set<PaymentDto> payments;
    private Set<ProjectDto> myProjects;
    private Set<AchievementDto> achievemets;
    private Set<CommentDto> comments;
    private Set<ProjectNewsDto> projectNews;
}
