package io.funwork.api.organization.service;

import io.funwork.api.organization.domain.Department;
import io.funwork.api.organization.domain.DepartmentPerson;
import io.funwork.api.organization.domain.Person;
import io.funwork.api.organization.domain.SecurityGrade;
import io.funwork.api.organization.domain.support.command.PersonCommand;
import io.funwork.api.organization.repository.DepartmentPersonRepository;
import io.funwork.api.organization.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DepartmentPersonRepository departmentPersonRepository;

    @Transactional
    public Person savePerson(PersonCommand personCommand) {

        Person person = Person.createPerson(personCommand);
        person = personRepository.save(person);

        //부서등록
        if (personCommand.getDeptId() != null) {

            Department department = new Department();
            department.setId(personCommand.getDeptId());

            DepartmentPerson departmentPerson = saveDepartmentPerson(person, department);
            person.addDepartment(departmentPerson);
        }

        return person;
    }

    private DepartmentPerson saveDepartmentPerson(Person person, Department department) {
        DepartmentPerson departmentPerson = new DepartmentPerson(person, department);
        return departmentPersonRepository.save(departmentPerson);
    }
}
