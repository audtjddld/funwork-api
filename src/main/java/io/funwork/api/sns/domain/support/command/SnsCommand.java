package io.funwork.api.sns.domain.support.command;

import io.funwork.api.sns.domain.FileSns;
import lombok.Data;

@Data
public class SnsCommand {
  private Long id;

  private String createDate;

  private String contents;

  private String personId;

  private FileSns fileSns;

}
