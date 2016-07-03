package io.funwork.api.sns.fixture;

import io.funwork.api.sns.domain.CommentSns;
import io.funwork.api.sns.domain.LikeSns;
import io.funwork.api.sns.domain.Sns;

public class LikeSnsFixture {
  private Long id;
  private String personId;
  private String createDate;
  private String contents;
  private String useYn = "N";
  private Sns sns;

  public static LikeSnsFixture anLikeSns() {
    return new LikeSnsFixture();
  }

  public LikeSnsFixture withId(Long id) {
    this.id = id;
    return this;
  }

  public LikeSnsFixture withPersonId(String personId) {
    this.personId = personId;
    return this;
  }

  public LikeSnsFixture withCreateDate(String createDate) {
    this.createDate = createDate;
    return this;
  }

  public LikeSnsFixture withUseYn(String useYn) {
    this.useYn = useYn;
    return this;
  }

  public LikeSnsFixture withSns(Sns sns) {
    this.sns = sns;
    return this;
  }

  public LikeSns build() {
    LikeSns likeSns = new LikeSns();
    likeSns.setId(id);
    likeSns.setCreateDate(createDate);
    likeSns.setPersonId(personId);
    likeSns.setSns(sns);

    return likeSns;
  }
}
