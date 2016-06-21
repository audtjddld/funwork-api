package io.funwork.api.sns.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import io.funwork.FunworkApiApplicationTests;
import io.funwork.api.sns.domain.Sns;
import io.funwork.api.sns.fixture.SnsFixture;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FunworkApiApplicationTests.class)
@ActiveProfiles(profiles = "test")
public class SnsIntegrationTest {

  @Autowired
  private SnsRepository snsRepository;

  private Sns sns;
  private Sns sns2;
  private Sns sns3;

  @Before
  public void setUp() {
    sns = createSnsFixture()
        .withContents("testest11")
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
    assertThat(snsList.get(0).getContents(), is("testest11"));

  }

  @Test
  public void test_add_sns() throws Exception {

    //given
    //when
    Sns saveSns = saveSns();


    System.out.println("saveSns.getId()::" + saveSns.getId());
    //then
    assertThat(saveSns.getId(), is(1L));

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
