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

import io.funwork.api.sns.domain.FileSns;
import io.funwork.api.sns.domain.Sns;
import io.funwork.api.sns.domain.support.command.SnsCommand;
import io.funwork.api.sns.fixture.SnsFixture;
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

  Sns givenSns;
  Sns expectSns;
  Sns snsExpectFile;

  @Before
  public void setUp() {
    SnsFixture snsFixture = createSnsFixture();
    givenSns = snsFixture.build();
    expectSns = snsFixture.withId(1L).build();
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

  private SnsFixture createSnsFixture() {
    return SnsFixture.anSns()
        .withId(1L)
        .withContents("안녕하세요..")
        .withCreateDate("2016-06-16")
        .withPersonId("urosaria")
        ;
  }

  private Sns createSnsFile() {
    SnsFixture fixture = createSnsFixture();
    Sns sns = fixture.withId(1L).withPersonId("urosaria").build();
    List<FileSns> fileSnsList = createFileSnsFixture(sns);
    sns.setFileSnsList(fileSnsList);
    return sns;
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

  private List<Sns> createSnsListFixture() {
    Sns sns1 = new Sns();
    FileSns fileSns = new FileSns();

    sns1.setContents("testest");
    sns1.setCreateDate("2016-06-16");
    sns1.setPersonId("urosaria");

    fileSns.setFileNm("test.jpg");
    fileSns.setPath("/test/test/");
    fileSns.setSns(sns1);
    fileSns.setFileOrder(1);
    fileSns.setId(1L);

    List<Sns> snsList = new ArrayList<>();
    List<FileSns> fileSnsList = new ArrayList<>();
    fileSnsList.add(0,fileSns);
    sns1.setFileSnsList(fileSnsList);
    snsList.add(0,sns1);
    return snsList;
  }

}