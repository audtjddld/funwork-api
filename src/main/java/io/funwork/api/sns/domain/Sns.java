package io.funwork.api.sns.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.funwork.api.sns.domain.support.command.SnsCommand;
import lombok.Data;

@Entity
@Data
public class Sns implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String personId;

  private String createDate;

  private String contents;

  @NotNull
  private String useYn = "N";

  private String deptId;

  private int likeCount;

  public static Sns createSns(SnsCommand snsCommand) {
    Sns sns = new Sns();
    sns.setId(snsCommand.getId());
    sns.setContents(snsCommand.getContents());
    sns.setCreateDate(snsCommand.getCreateDate());
    sns.setPersonId(snsCommand.getPersonId());
    return sns;
  }
}
