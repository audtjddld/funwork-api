package io.funwork.api.sns.domain;


import io.funwork.api.sns.domain.support.command.CommentSnsCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class CommentSns {

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
        //commentSns.setId(1L);
        commentSns.setContents(commentSnsCommand.getContents());
        commentSns.setCreateDate(commentSnsCommand.getCreateDate());
        commentSns.setPersonId(commentSnsCommand.getPersonId());
        commentSns.setSns(commentSnsCommand.getSns());

        return commentSns;
    }

}
