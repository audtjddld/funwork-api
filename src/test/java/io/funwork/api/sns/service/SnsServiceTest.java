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

import io.funwork.api.sns.domain.Sns;
import io.funwork.api.sns.domain.support.command.SnsCommand;
import io.funwork.api.sns.fixture.SnsFixture;
import io.funwork.api.sns.repository.SnsRepository;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
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

  Sns givenSns;
  Sns expectSns;

  @Before
  public void setUp() {
    SnsFixture snsFixture = createSnsFixture();
    givenSns = snsFixture.build();
    expectSns = snsFixture.withId(1L).build();
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
  }

  @Test
  public void test_add_sns() throws Exception {

    //given
    SnsCommand snsCommand = createSnsCommandFixture();
    when(snsRepository.save(givenSns)).thenReturn(expectSns);

    //when
    Sns saveSns = snsService.saveSns(snsCommand);

    //then
    verify(snsRepository).save(givenSns);
    assertThat(saveSns.getId(), is(expectSns.getId()));
  }

  private SnsFixture createSnsFixture() {
    return SnsFixture.anSns()
        .withId(1L)
        .withContents("안녕하세요..")
        .withCreateDate("2016-06-16")
        .withPersonId("urosaria")
        ;
  }

  private Sns createSns() {
    SnsFixture fixture = createSnsFixture();
    Sns sns = fixture.withId(1L).withPersonId("urosaria").build();

    return sns;
  }

  private SnsCommand createSnsCommandFixture() {
    SnsCommand snsCommand = new SnsCommand();
    snsCommand.setId(givenSns.getId());
    snsCommand.setContents(givenSns.getContents());
    snsCommand.setCreateDate(givenSns.getCreateDate());
    snsCommand.setPersonId(givenSns.getPersonId());
    return snsCommand;
  }

  private List<Sns> createSnsListFixture() {
    Sns sns1 = new Sns();
    sns1.setContents("testest");
    sns1.setCreateDate("2016-06-16");
    sns1.setPersonId("urosaria");

    List<Sns> snsList = new ArrayList<>();
    snsList.add(0,sns1);
    return snsList;
  }
}
