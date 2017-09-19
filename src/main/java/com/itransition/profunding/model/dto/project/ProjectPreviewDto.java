package com.itransition.profunding.model.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 19.09.2017 18:38
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProjectPreviewDto {

    private Long id;
    private String title;
    private Date completionDate;
    private String description;
    private String image;
    private Long totalCost;
}
