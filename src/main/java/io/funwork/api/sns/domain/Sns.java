package io.funwork.api.sns.domain;

import io.funwork.api.sns.domain.support.command.SnsCommand;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Sns implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "sns_id")
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

    @OneToMany(mappedBy = "sns", fetch = FetchType.EAGER)
    private List<LikeSns> likeSnsList = new ArrayList<>();

    public void addCommentSns(CommentSns commentSns) {
        if (isNotBelongCommentSns(commentSns)) {
            this.commentSnsList.add(commentSns);
        }
    }

    private boolean isNotBelongCommentSns(CommentSns commentSns) {
        return commentSns != null && !commentSnsList.contains(commentSns);
    }

    public void addLikeSns(LikeSns likeSns) {
        if (isNotBelongLikeSns(likeSns)) {
            this.likeSnsList.add(likeSns);
        }
    }

    private boolean isNotBelongLikeSns(LikeSns likeSns) {
        return likeSns != null && !likeSnsList.contains(likeSns);
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
