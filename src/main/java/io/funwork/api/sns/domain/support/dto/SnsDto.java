package io.funwork.api.sns.domain.support.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SnsDto {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String person_id;

  private String create_date;

  private String contents;

  private String useYn = "N";

  private String dept_id;

  private int like_count;
}
