package io.funwork.api.organization.service;

import io.funwork.api.organization.domain.Department;
import io.funwork.api.organization.domain.DepartmentPerson;
import io.funwork.api.organization.domain.Person;
import io.funwork.api.organization.domain.support.command.PersonCommand;
import io.funwork.api.organization.domain.support.dto.OrganizationTreeDto;
import io.funwork.api.organization.repository.DepartmentPersonRepository;
import io.funwork.api.organization.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Transactional
    public OrganizationTreeDto getTreeByPerson(PersonCommand personCommand) {

        Person person = Person.createPerson(personCommand);
        person = personRepository.findOne(person.getId());

        Department department = getDepartmentByPerson(person);

        OrganizationTreeDto tree = new OrganizationTreeDto();

        Department parentDept = department.getParentDept();

        if (parentDept != null) {
            tree.setTitle(parentDept.getName());
            tree.setKey(String.valueOf(parentDept.getId()));
            tree.setType("DEPT");

            OrganizationTreeDto childTree = new OrganizationTreeDto();
            childTree.setTitle(department.getName());
            childTree.setKey(String.valueOf(department.getId()));
            childTree.setType("DEPT");

            OrganizationTreeDto personTree = new OrganizationTreeDto();
            personTree.setTitle(person.getName());
            personTree.setKey(String.valueOf(person.getId()));
            personTree.setType("USER");

            List<OrganizationTreeDto> personTrees = new ArrayList<>();
            personTrees.add(personTree);
            childTree.setChildren(personTrees);

            List<OrganizationTreeDto> childTrees = new ArrayList<>();
            childTrees.add(childTree);

            tree.setChildren(childTrees);
        }

        return tree;
    }


    private Department getDepartmentByPerson(Person person) {
        if (person.getDepartmentPersons().size() > 0) {
            return person.getDepartmentPersons().get(0).getDepartment();
        }

        return null;
    }

    private DepartmentPerson saveDepartmentPerson(Person person, Department department) {
        DepartmentPerson departmentPerson = new DepartmentPerson(person, department);
        return departmentPersonRepository.save(departmentPerson);
    }
}
