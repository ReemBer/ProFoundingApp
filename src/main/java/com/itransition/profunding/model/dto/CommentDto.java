package com.itransition.profunding.model.dto;

import com.itransition.profunding.model.dto.user.UserViewDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CommentDto {
    private Long id;
    private Date dateCreated;
    private Long projectId;
    private String content;
    private UserDto user;
}
