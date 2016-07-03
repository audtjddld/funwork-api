package io.funwork.api.sns.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import io.funwork.api.sns.domain.support.command.LikeSnsCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter @Setter
public class LikeSns {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "like_id")
  private Long id;

  private String personId;

  private String createDate;

  @NotNull
  private String useYn = "Y";

  @ManyToOne
  @JoinColumn(name = "sns_id")
  private Sns sns;

  public static LikeSns createLikeSns(LikeSnsCommand likeSnsCommand) {
    LikeSns likeSns = new LikeSns();
    //commentSns.setId(commentSnsCommand.getId());
    //commentSns.setId(1L);
    likeSns.setCreateDate(likeSnsCommand.getCreateDate());
    likeSns.setPersonId(likeSnsCommand.getPersonId());
    likeSns.setSns(likeSnsCommand.getSns());

    return likeSns;
  }

}
