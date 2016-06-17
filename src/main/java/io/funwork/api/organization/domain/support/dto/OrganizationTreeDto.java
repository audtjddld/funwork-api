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
    private String info;
    private List<OrganizationTreeDto> children = new ArrayList<>();


    public static OrganizationTreeDto createTree(Department department) {
        OrganizationTreeDto organizationTreeDto = new OrganizationTreeDto();
        organizationTreeDto.setTitle(department.getName());
        organizationTreeDto.setKey(String.valueOf(department.getId()));
        organizationTreeDto.setType("DEPT");
        return organizationTreeDto;
    }

    public static OrganizationTreeDto createTree(Person person) {
        OrganizationTreeDto organizationTreeDto = new OrganizationTreeDto();
        organizationTreeDto.setTitle(person.getName());
        organizationTreeDto.setKey(String.valueOf(person.getId()));
        organizationTreeDto.setType("USER");
        return organizationTreeDto;
    }
}
