package io.funwork.api.sns.fixture;

import io.funwork.api.sns.domain.CommentSns;
import io.funwork.api.sns.domain.Sns;

public class CommentSnsFixture {
    private Long id;
    private String personId;
    private String createDate;
    private String contents;
    private String useYn = "N";
    private Sns sns;
    //private List<FileSns> fileSns = new ArrayList<>();


    public static CommentSnsFixture anCommentSns() {
        return new CommentSnsFixture();
    }

    public CommentSnsFixture withId(Long id) {
        this.id = id;
        return this;
    }

    public CommentSnsFixture withPersonId(String personId) {
        this.personId = personId;
        return this;
    }

    public CommentSnsFixture withCreateDate(String createDate) {
        this.createDate = createDate;
        return this;
    }

    public CommentSnsFixture withContents(String contents) {
        this.contents = contents;
        return this;
    }

    public CommentSnsFixture withUseYn(String useYn) {
        this.useYn = useYn;
        return this;
    }

//  public CommentSnsFixture withFileSns(List<FileSns> fileSns) {
//    this.fileSns = fileSns;
//    return this;
//  }

    public CommentSnsFixture withSns(Sns sns) {
        this.sns = sns;
        return this;
    }

    public CommentSns build() {
        CommentSns commentSns = new CommentSns();
        commentSns.setId(id);
        commentSns.setContents(contents);
        commentSns.setCreateDate(createDate);
        commentSns.setPersonId(personId);
        commentSns.setSns(sns);
        //sns.setFileSnsList(fileSns);

        return commentSns;
    }
}
