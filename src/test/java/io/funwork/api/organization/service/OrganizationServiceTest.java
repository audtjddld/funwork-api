package io.funwork.api.organization.service;

import io.funwork.api.organization.domain.Department;
import io.funwork.api.organization.domain.DepartmentPerson;
import io.funwork.api.organization.domain.Person;
import io.funwork.api.organization.domain.SecurityGrade;
import io.funwork.api.organization.domain.support.command.PersonCommand;
import io.funwork.api.organization.fixture.PersonFixture;
import io.funwork.api.organization.repository.DepartmentPersonRepository;
import io.funwork.api.organization.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles(profiles = "test")
public class OrganizationServiceTest {

    @InjectMocks
    OrganizationService organizationService = new OrganizationService();

    @Mock
    PersonRepository personRepository;

    @Mock
    DepartmentPersonRepository departmentPersonRepository;

    Person givenPerson;
    Person expectPerson;

    @Before
    public void setUp() {
        PersonFixture personFixture = createPersonFixture();
        givenPerson = personFixture.build();
        expectPerson = personFixture.withId(1L).build();
    }

    @Test
    public void test_mock_organization_service() throws Exception {
        assertNotNull(organizationService);
    }

    @Test
    public void test_add_person() throws Exception {
        //given
        PersonCommand personCommand = createPersonCommandFixture();
        when(personRepository.save(givenPerson)).thenReturn(expectPerson);

        //when
        Person savePerson = organizationService.savePerson(personCommand);

        //then
        verify(personRepository).save(givenPerson);
        assertThat(savePerson.getId(), is(expectPerson.getId()));
    }

    @Test
    public void test_add_person_and_belong_dept() throws Exception {

        //given
        PersonCommand personCommand = createPersonCommandFixture();
        personCommand.setDeptId(1L);
        when(personRepository.save(givenPerson)).thenReturn(expectPerson);

        Department department = new Department();
        department.setId(1L);

        DepartmentPerson expectDepartmentPerson = new DepartmentPerson();
        expectDepartmentPerson.setId(1L);
        expectDepartmentPerson.setDepartment(department);
        expectDepartmentPerson.setPerson(expectPerson);

        when(departmentPersonRepository.save(any(DepartmentPerson.class))).thenReturn(expectDepartmentPerson);

        //when
        Person savePerson = organizationService.savePerson(personCommand);

        //then
        verify(departmentPersonRepository).save(any(DepartmentPerson.class));
        assertThat(savePerson.getId(), is(expectPerson.getId()));
        assertThat(savePerson.getDepartmentPersons().size(), is(1));
    }

    private PersonCommand createPersonCommandFixture() {
        PersonCommand personCommand = new PersonCommand();
        personCommand.setEmail(givenPerson.getEmail());
        personCommand.setPasswd(givenPerson.getPasswd());
        personCommand.setPosition(givenPerson.getPosition());
        personCommand.setSecurityGrade(givenPerson.getSecurityGrade());
        return personCommand;
    }

    private PersonFixture createPersonFixture() {
        return PersonFixture.anPerson()
                .withEmail("test1234@funwork.io")
                .withPasswd("test1234!")
                .withPosition("사원")
                .withSecurityGrade(SecurityGrade.NORMAL);
    }
}