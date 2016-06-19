package io.funwork.api.organization.domain.support.dto;

import java.util.ArrayList;
import java.util.List;

import io.funwork.api.organization.domain.Department;
import io.funwork.api.organization.domain.Person;
import lombok.Data;

@Data
public class OrganizationTreeDto {

    private String title;
    private String key;
    private String type;
    private Object info;
    private List<OrganizationTreeDto> children = new ArrayList<>();


    public static OrganizationTreeDto createTree(Department department) {
        Department parentDept = department.getParentDept();
        OrganizationTreeDto organizationTreeDto = new OrganizationTreeDto();
        organizationTreeDto.setTitle(department.getName());
        organizationTreeDto.setKey((parentDept != null) ? "DEPT"+parentDept.getId()+"-"+"DEPT"+department.getId() : "DEPT"+department.getId());
        organizationTreeDto.setType("DEPT");
        organizationTreeDto.setInfo(new DepartmentInfoDto(department));
        return organizationTreeDto;
    }

    public static OrganizationTreeDto createTree(Person person, Long deptId) {
        OrganizationTreeDto organizationTreeDto = new OrganizationTreeDto();
        organizationTreeDto.setTitle(person.getName());
        organizationTreeDto.setKey("DEPT"+deptId + "-" + "USER"+person.getId());
        organizationTreeDto.setType("USER");
        organizationTreeDto.setInfo(new PersonInfoDto(person));
        return organizationTreeDto;
    }
}
