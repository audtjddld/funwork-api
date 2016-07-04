package io.funwork.api.sns.domain.support.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class SnsDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String personId;

    private String createDate;

    private String contents;

    private String useYn = "N";

    private String deptId;

    private int likeCount;
}
