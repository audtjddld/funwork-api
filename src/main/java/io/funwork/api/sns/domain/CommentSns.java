package io.funwork.api.sns.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import io.funwork.api.sns.domain.support.command.CommentSnsCommand;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter @Setter
public class CommentSns {

  //public CommentSns(Sns sns){
  //  this.sns = sns;
  //}

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "comment_id")
  private Long id;

  private String contents;

  private String personId;

  private String createDate;

  @NotNull
  private String useYn = "Y";

  @ManyToOne
  @JoinColumn(name = "sns_id")
  private Sns sns;

  public static CommentSns createCommentSns(CommentSnsCommand commentSnsCommand) {
    CommentSns commentSns = new CommentSns();
    //commentSns.setId(commentSnsCommand.getId());
    commentSns.setId(1L);
    commentSns.setContents(commentSnsCommand.getContents());
    commentSns.setCreateDate(commentSnsCommand.getCreateDate());
    commentSns.setPersonId(commentSnsCommand.getPersonId());
    commentSns.setSns(commentSnsCommand.getSns());


    System.out.println("commnet:" + commentSns.getId());
    System.out.println("commnet:" + commentSns.getSns().getId());

    return commentSns;
  }

}
