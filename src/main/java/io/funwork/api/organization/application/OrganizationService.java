package io.funwork.api.organization.application;

import io.funwork.api.organization.domain.Department;
import io.funwork.api.organization.domain.DepartmentPerson;
import io.funwork.api.organization.domain.Person;
import io.funwork.api.organization.domain.support.command.PersonCommand;
import io.funwork.api.organization.domain.support.dto.OrganizationTreeDto;
import io.funwork.api.organization.application.exception.NotFoundDepartment;
import io.funwork.api.organization.application.exception.NotFoundPerson;
import io.funwork.api.organization.infrastructure.jpa.DepartmentPersonRepository;
import io.funwork.api.organization.infrastructure.jpa.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        person.addDepartment(addDepartment(personCommand, person));
        return person;
    }

    private DepartmentPerson addDepartment(PersonCommand personCommand, Person person) {
        if (personCommand.isExistDeptId()) {
            DepartmentPerson departmentPerson = saveDepartmentPerson(person, personCommand.getDeptId());
            return departmentPerson;
        }
        throw new IllegalArgumentException("사용자 등록시 부서 정보는 필수값 입니다");
    }

    @Transactional
    public OrganizationTreeDto getTreeByPerson(Long id) {

        Person person = personRepository.findOne(id);
        if(person == null) throw new NotFoundPerson();
        Department department = getDepartmentByPerson(person);
        if(department == null) throw new NotFoundDepartment();
        OrganizationTreeDto tree = makeTree(department, null);
        return tree;
    }

    private OrganizationTreeDto makeTree(Department department, OrganizationTreeDto childTree) {
        OrganizationTreeDto treeDto = new OrganizationTreeDto(department);
        treeDto.setChildren(childs(department, childTree));
        Department parentDept = department.getParentDept();
        if (parentDept != null) return makeTree(parentDept, treeDto);
        return treeDto;
    }

    private List<OrganizationTreeDto> childs(Department department, OrganizationTreeDto tree) {
        List<OrganizationTreeDto> childs = department.getPersons()
            .stream()
            .map(person -> new OrganizationTreeDto(person, department.getId()))
            .collect(Collectors.toList());

        if (tree != null) childs.add(tree);
        return childs;
    }

    private Department getDepartmentByPerson(Person person) {
        if (person.getDepartmentPersons().size() > 0) {
            return person.getDepartmentPersons().get(0).getDepartment();
        }

        return null;
    }

    private DepartmentPerson saveDepartmentPerson(Person person, Long deptId) {
        Department department = new Department();
        department.setId(deptId);
        DepartmentPerson departmentPerson = new DepartmentPerson(person, department);
        return departmentPersonRepository.save(departmentPerson);
    }
}
