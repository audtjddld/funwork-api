package io.funwork.api.sns.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.Comment;

import io.funwork.api.sns.domain.CommentSns;
import io.funwork.api.sns.domain.FileSns;
import io.funwork.api.sns.domain.Sns;
import io.funwork.api.sns.domain.support.command.CommentSnsCommand;
import io.funwork.api.sns.domain.support.command.SnsCommand;
import io.funwork.api.sns.fixture.SnsFixture;
import io.funwork.api.sns.fixture.CommentSnsFixture;
import io.funwork.api.sns.repository.CommentSnsRepository;
import io.funwork.api.sns.repository.FileSnsRepository;
import io.funwork.api.sns.repository.SnsRepository;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles(profiles = "test")
public class SnsServiceTest {

  @InjectMocks
  SnsService snsService = new SnsService();

  @Mock
  SnsRepository snsRepository;

  @Mock
  FileSnsRepository fileSnsRepository;

  @Mock
  CommentSnsRepository commentSnsRepository;

  Sns givenSns;
  Sns expectSns;
  Sns snsExpectFile;

  CommentSns givenCommentSns;
  CommentSns expectCommentSns;

  @Before
  public void setUp() {
    SnsFixture snsFixture = createSnsFixture();
    CommentSnsFixture commentSnsFixture = createCommentSnsFixture();

    givenSns = snsFixture.build();
    givenCommentSns = commentSnsFixture.build();

    expectSns = snsFixture.withId(1L).build();
    expectCommentSns = commentSnsFixture.withId(1L).build();

    snsExpectFile = createSnsFile();


  }

  @Test
  public void test_mock_sns_service() throws Exception {
    assertNotNull(snsService);
  }

  @Test
  public void test_getlist_by_person() throws Exception {

    //given
    when(snsRepository.findAll()).thenReturn(createSnsListFixture());

    //when
    List<Sns> snsList = snsService.getSnsList();

    //then
    log.info(snsList.toString());
    verify(snsRepository).findAll();

    assertThat(snsList.get(0).getContents(), is("testest"));
    //첨부파일 체크
    assertThat(snsList.get(0).getFileSnsList().get(0).getFileNm(), is("test.jpg"));
    assertThat(snsList.get(0).getFileSnsList().get(1).getFileNm(), is("test2.jpg"));
    //댓글 체크
    assertThat(snsList.get(0).getCommentSnsList().get(1).getContents(), is("댓글2"));
  }


  @Test
  public void test_add_sns() throws Exception {

    //given
    SnsCommand snsCommand = createSnsCommandFixture();
    when(snsRepository.save(givenSns)).thenReturn(expectSns);

    Sns sns = new Sns();
    sns.setId(1L);

    FileSns expectFileSns = new FileSns();
    expectFileSns.setId(1L);
    expectFileSns.setFileOrder(1);
    expectFileSns.setPath("/test/test/");
    expectFileSns.setFileNm("test.jpg");
    expectFileSns.setSns(sns);

    when(fileSnsRepository.save(any(FileSns.class))).thenReturn(expectFileSns);

    //when
    Sns saveSns = snsService.saveSns(snsCommand);
    log.info(saveSns.toString());

    //then
    verify(fileSnsRepository).save(any(FileSns.class));
    assertThat(saveSns.getId(), is(expectSns.getId()));
    assertThat(saveSns.getFileSnsList().get(0).getFileNm(), is("test.jpg"));

  }

  @Test
  public void test_add_comment_sns() throws Exception{

    //given
    SnsCommand snsCommand = createSnsCommandFixture();
    when(snsRepository.save(givenSns)).thenReturn(expectSns);
    Sns saveSns = snsService.saveSns(snsCommand);
    log.info(saveSns.toString());

//
//    Sns sns = new Sns();
//    sns.setId(1L);
//
//    CommentSns expectCommentSns = new CommentSns();
//    expectCommentSns.setId(1L);
//    expectCommentSns.setSns(sns);
//    expectCommentSns.setContents("댓글1");
//    expectCommentSns.setCreateDate("2016-06-27");
//
//    when(commentSnsRepository.save(any(CommentSns.class))).thenReturn(expectCommentSns);


    //when(commentSnsRepository.save(givenCommentSns)).thenReturn(expectCommentSns);

    //when
    CommentSnsCommand commentSnsCommand = createCommentSnsCommandFixture();
    commentSnsCommand.setSns(saveSns);
    List<CommentSns> saveCommentSns = snsService.saveCommentSns(commentSnsCommand);

    //then
    verify(commentSnsRepository).save(any(CommentSns.class));

    //assertThat(saveCommentSns.get(0).getId(), is(expectCommentSns.getId()));

  }

  private SnsFixture createSnsFixture() {
    return SnsFixture.anSns()
        .withId(1L)
        .withContents("안녕하세요..")
        .withCreateDate("2016-06-16")
        .withPersonId("urosaria")
        ;
  }

  private CommentSnsFixture createCommentSnsFixture() {
    Sns sns = new Sns();
    sns.setId(1L);
    return CommentSnsFixture.anCommentSns()
        .withId(1L)
        .withContents("댓글1")
        .withCreateDate("2016-06-16")
        .withPersonId("urosaria")
        .withSns(sns)
        ;
  }

  private Sns createSnsFile() {
    SnsFixture fixture = createSnsFixture();
    Sns sns = fixture.withId(1L).withPersonId("urosaria").build();
    List<FileSns> fileSnsList = createFileSnsFixture(sns);
    sns.setFileSnsList(fileSnsList);
    return sns;
  }

  private CommentSns createCommentSnsFile() {
    CommentSnsFixture fixture = createCommentSnsFixture();
    CommentSns Commentsns = fixture.withId(1L).withPersonId("urosaria").build();
    return Commentsns;
  }

  private List<FileSns> createFileSnsFixture(Sns sns) {
    FileSns fileSns = new FileSns();
    fileSns.setId(1L);
    fileSns.setPath("/test/test/");
    fileSns.setFileNm("test.jpg");
    fileSns.setSns(sns);

    List<FileSns> fileSnsSnsList = new ArrayList<>();
    fileSnsSnsList.add(fileSns);
    return fileSnsSnsList;
  }

  private FileSns createFileSns() {
    FileSns fileSns = new FileSns();
    fileSns.setId(1L);
    fileSns.setPath("/test/test/");
    fileSns.setFileNm("test.jpg");
    return fileSns;
  }

  private SnsCommand createSnsCommandFixture() {
    SnsCommand snsCommand = new SnsCommand();
    snsCommand.setId(givenSns.getId());
    snsCommand.setContents(givenSns.getContents());
    snsCommand.setCreateDate(givenSns.getCreateDate());
    snsCommand.setPersonId(givenSns.getPersonId());
    snsCommand.setFileSns(createFileSns());
    return snsCommand;
  }

  private CommentSnsCommand createCommentSnsCommandFixture() {
    Sns sns = new Sns();
    sns.setId(1L);

    CommentSnsCommand commentSnsCommand = new CommentSnsCommand();
    commentSnsCommand.setId(givenCommentSns.getId());
    commentSnsCommand.setContents(givenCommentSns.getContents());
    commentSnsCommand.setCreateDate(givenCommentSns.getCreateDate());
    commentSnsCommand.setPersonId(givenCommentSns.getPersonId());
    //commentSnsCommand.setFileSns(createFileSns());
    commentSnsCommand.setSns(sns);

    return commentSnsCommand;
  }

  private List<Sns> createSnsListFixture() {
    Sns sns1 = new Sns();
    FileSns fileSns = new FileSns();
    FileSns fileSns2 = new FileSns();

    CommentSns commentSns = new CommentSns();
    CommentSns commentSns2 = new CommentSns();

    sns1.setId(1L);
    sns1.setContents("testest");
    sns1.setCreateDate("2016-06-16");
    sns1.setPersonId("urosaria");

    fileSns.setFileNm("test.jpg");
    fileSns.setPath("/test/test/");
    fileSns.setSns(sns1);
    fileSns.setFileOrder(1);
    fileSns.setId(1L);

    fileSns2.setFileNm("test2.jpg");
    fileSns2.setPath("/test/test/");
    fileSns2.setSns(sns1);
    fileSns2.setFileOrder(2);
    fileSns2.setId(2L);

    commentSns.setContents("댓글1");
    commentSns.setCreateDate("2016-06-26");
    commentSns.setId(1L);
    commentSns.setPersonId("urosaria");
    commentSns.setUseYn("Y");
    commentSns.setSns(sns1);

    commentSns2.setContents("댓글2");
    commentSns2.setCreateDate("2016-06-26");
    commentSns2.setId(2L);
    commentSns2.setPersonId("test");
    commentSns2.setUseYn("Y");
    commentSns2.setSns(sns1);

    List<Sns> snsList = new ArrayList<>();
    List<FileSns> fileSnsList = new ArrayList<>();
    List<CommentSns> commentSnsList = new ArrayList<>();
    fileSnsList.add(0,fileSns);
    fileSnsList.add(1,fileSns2);
    commentSnsList.add(0,commentSns);
    commentSnsList.add(1,commentSns2);
    sns1.setFileSnsList(fileSnsList);
    sns1.setCommentSnsList(commentSnsList);
    snsList.add(0,sns1);
    return snsList;
  }

}