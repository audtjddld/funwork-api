package io.funwork.api.sns.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Sns implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String person_id;

  private String create_date;

  private String contents;

  @NotNull
  private String useYn = "N";

  private String dept_id;

  private int like_count;

}
