package io.funwork.api.sns.domain.support.command;

import lombok.Data;

@Data
public class SnsCommand {
  private Long id;

  private String createDate;

  private String contents;

  private String personId;

}
