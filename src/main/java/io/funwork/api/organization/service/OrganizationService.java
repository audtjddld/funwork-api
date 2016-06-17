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

        OrganizationTreeDto tree = makeParentDepartmentTree(department, null);
        return tree;
    }

    private OrganizationTreeDto makeParentDepartmentTree(Department department, OrganizationTreeDto tree) {

        OrganizationTreeDto treeDto = new OrganizationTreeDto();
        treeDto.setTitle(department.getName());
        treeDto.setKey(String.valueOf(department.getId()));
        treeDto.setType("DEPT");

        List<OrganizationTreeDto> childs = new ArrayList<>();

        department.getDepartmentPersons().stream().filter(departmentPerson -> departmentPerson.getPerson() != null).forEach(departmentPerson -> {
            OrganizationTreeDto personDto = new OrganizationTreeDto();
            Person person = departmentPerson.getPerson();
            personDto.setTitle(person.getName());
            personDto.setKey(String.valueOf(person.getId()));
            personDto.setType("USER");
            childs.add(personDto);
        });

        if(tree != null) childs.add(tree);
        treeDto.setChildren(childs);

        Department parentDept = department.getParentDept();
        if(parentDept != null )
            return makeParentDepartmentTree(parentDept, treeDto);
        return treeDto;
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
