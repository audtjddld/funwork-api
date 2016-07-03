package io.funwork.api.sns.domain.support.command;

import io.funwork.api.sns.domain.Sns;
import lombok.Data;

@Data
public class LikeSnsCommand {

  private Long id;

  private String createDate;

  private String personId;

  private Long snsId;

  private Sns sns;

}
