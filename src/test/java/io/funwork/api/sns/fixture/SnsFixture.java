package io.funwork.api.sns.fixture;

import io.funwork.api.sns.domain.FileSns;
import io.funwork.api.sns.domain.Sns;

import java.util.ArrayList;
import java.util.List;

public class SnsFixture {

    private Long id;
    private String personId;
    private String createDate;
    private String contents;
    private String useYn = "N";
    private String deptId;
    private int likeCount;
    private List<FileSns> fileSns = new ArrayList<>();


    public static SnsFixture anSns() {
        return new SnsFixture();
    }

    public SnsFixture withId(Long id) {
        this.id = id;
        return this;
    }

    public SnsFixture withPersonId(String personId) {
        this.personId = personId;
        return this;
    }

    public SnsFixture withCreateDate(String createDate) {
        this.createDate = createDate;
        return this;
    }

    public SnsFixture withContents(String contents) {
        this.contents = contents;
        return this;
    }

    public SnsFixture withUseYn(String useYn) {
        this.useYn = useYn;
        return this;
    }

    public SnsFixture withDeptId(String deptId) {
        this.deptId = deptId;
        return this;
    }

    public SnsFixture withLikeCount(int likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public SnsFixture withFileSns(List<FileSns> fileSns) {
        this.fileSns = fileSns;
        return this;
    }

    public Sns build() {
        Sns sns = new Sns();
        sns.setId(id);
        sns.setContents(contents);
        sns.setCreateDate(createDate);
        sns.setPersonId(personId);
        sns.setDeptId(deptId);
        sns.setLikeCount(likeCount);
        sns.setUseYn(useYn);
        sns.setFileSnsList(fileSns);

        return sns;
    }
}
