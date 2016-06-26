package io.funwork.api.sns.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter @Setter
public class FileSns {

  public FileSns(Sns sns){
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
