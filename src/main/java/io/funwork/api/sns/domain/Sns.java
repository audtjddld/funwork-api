package io.funwork.api.sns.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import io.funwork.api.organization.domain.DepartmentPerson;
import io.funwork.api.sns.domain.support.command.SnsCommand;
import lombok.Data;

@Entity
@Data
public class Sns implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name="sns_id")
  private Long id;

  private String personId;

  private String createDate;

  private String contents;

  @NotNull
  private String useYn = "N";

  private String deptId;

  private int likeCount;

  @OneToMany(mappedBy = "sns", fetch = FetchType.EAGER)
  private List<FileSns> fileSnsList = new ArrayList<>();

  @OneToMany(mappedBy = "sns", fetch = FetchType.EAGER)
  private List<CommentSns> commentSnsList = new ArrayList<>();

  public void addCommentSns(CommentSns commentSns) {
    if (isNotBelongCommentSns(commentSns)) {
      this.commentSnsList.add(commentSns);
    }
  }

  private boolean isNotBelongCommentSns(CommentSns commentSns) {
    return commentSns != null && !commentSnsList.contains(commentSns);
  }

  public void addFileSns(FileSns fileSns) {
    if (isNotBelongFileSns(fileSns)) {
      this.fileSnsList.add(fileSns);
    }
  }

  private boolean isNotBelongFileSns(FileSns fileSns) {
    return fileSns != null && !fileSnsList.contains(fileSns);
  }

  public static Sns createSns(SnsCommand snsCommand) {
    Sns sns = new Sns();
    sns.setId(snsCommand.getId());
    sns.setContents(snsCommand.getContents());
    sns.setCreateDate(snsCommand.getCreateDate());
    sns.setPersonId(snsCommand.getPersonId());
    return sns;
  }
}
