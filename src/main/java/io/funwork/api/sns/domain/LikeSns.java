package io.funwork.api.sns.domain;


import io.funwork.api.sns.domain.support.command.LikeSnsCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@Getter
@Setter
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
