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
import io.funwork.api.sns.domain.FileSns;
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

  private Sns sns;
  private Sns sns2;
  private Sns sns3;

  @Before
  public void setUp() {
    sns = createSnsFixture()
        .withContents("testest222")
        .withId(1L)
        .build();
    sns2 = createSnsFixture()
        .withContents("testest22")
        .withId(2L)
        .build();
    sns3 = createSnsFixture()
        .withContents("testest22")
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
