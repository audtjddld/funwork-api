package io.funwork.api.sns.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class FileSns {

    public FileSns(Sns sns) {
        this.sns = sns;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "file_id")
    private Long id;

    private int fileOrder;

    private String path;

    private String fileNm;

    @NotNull
    private String useYn = "N";

    @ManyToOne
    @JoinColumn(name = "sns_id")
    private Sns sns;

}
