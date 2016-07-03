package io.funwork.api.sns.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.List;

import io.funwork.FunworkApiApplicationTests;
import io.funwork.api.organization.domain.Department;
import io.funwork.api.organization.domain.DepartmentPerson;
import io.funwork.api.sns.domain.CommentSns;
import io.funwork.api.sns.domain.FileSns;
import io.funwork.api.sns.domain.LikeSns;
import io.funwork.api.sns.domain.Sns;
import io.funwork.api.sns.fixture.SnsFixture;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FunworkApiApplicationTests.class)
@ActiveProfiles(profiles = "test")
public class SnsIntegrationTest {

  @Autowired
  private SnsRepository snsRepository;

  @Autowired
  private FileSnsRepository fileSnsRepository;

  @Autowired
  private CommentSnsRepository commentSnsRepository;

  @Autowired
  private LikeSnsRepository likeSnsRepository;

  private Sns sns;
  private Sns sns2;
  private Sns sns3;

  @Before
  public void setUp() {
    sns = createSnsFixture()
        .withContents("안녕하세요, 테스트1입니다.")
        .withId(1L)
        .build();
    sns2 = createSnsFixture()
        .withContents("안녕하세요, 테스트2입니다.")
        .withId(2L)
        .build();
    sns3 = createSnsFixture()
        .withContents("안녕하세요, 테스트3입니다.")
        .withId(3L)
        .build();
  }

  @Test
  public void test_getlist_by_person() throws Exception {

    //given
    List<Sns> snsList = snsRepository.findAll();

    //then
    assertThat(snsList.get(0).getContents(), is("안녕하세요, 테스트1입니다."));
    assertThat(snsList.get(0).getFileSnsList().get(0).getFileNm(), is("test.jpg"));
    assertThat(snsList.get(0).getCommentSnsList().get(0).getContents(), is("댓글테스트1"));

  }

  @Test
  public void test_add_sns() throws Exception {

    //given
    //when
    Sns saveSns = saveSns();

    FileSns fileSns = saveFileSns(saveSns);

    //then
    assertThat(saveSns.getId(), is(1L));
    assertThat(fileSns.getFileNm(), is("bbb.jpg"));

  }

  @Test
  public void test_add_commnet_sns() throws Exception {

    //given
    //when
    Sns saveSns = saveSns();

    CommentSns saveCommentSns = saveCommentSns(saveSns);

    //then
    assertThat(saveSns.getId(), is(1L));
    assertThat(saveCommentSns.getContents(), is("댓글1"));

  }

  @Test
  public void test_add_like_sns() throws Exception {

    //given
    //when
    Sns saveSns = saveSns();

    LikeSns saveLikeSns = saveLikeSns(saveSns);

    //then
    assertThat(saveSns.getId(), is(1L));
    assertThat(saveLikeSns.getSns().getId(), is(1L));

  }

  private FileSns saveFileSns(Sns sns) {
    FileSns fileSns = new FileSns();
    fileSns.setId(1L);
    fileSns.setFileOrder(1);
    fileSns.setSns(sns);
    fileSns.setFileNm("bbb.jpg");
    fileSns.setPath("/aaa/");
    fileSns.setUseYn("Y");
    fileSnsRepository.save(fileSns);

    return fileSns;
  }

  private CommentSns saveCommentSns(Sns sns) {
    CommentSns commentSns = new CommentSns();
    //commentSns.setId(1L);
    commentSns.setContents("댓글1");
    commentSns.setCreateDate("2016-06-27");
    commentSns.setPersonId("test1");
    commentSns.setUseYn("Y");
    commentSns.setSns(sns);
    commentSnsRepository.save(commentSns);

    return commentSns;
  }

  private LikeSns saveLikeSns(Sns sns) {
    LikeSns likeSns = new LikeSns();
    likeSns.setCreateDate("2016-06-27");
    likeSns.setPersonId("test1");
    likeSns.setUseYn("Y");
    likeSns.setSns(sns);
    likeSnsRepository.save(likeSns);

    return likeSns;
  }

  private SnsFixture createSnsFixture() {
    return SnsFixture.anSns()
        .withCreateDate("2016-06-16")
        .withPersonId("urosaria")
        ;
  }

  private Sns saveSns() {
    return snsRepository.save(sns);
  }


}
