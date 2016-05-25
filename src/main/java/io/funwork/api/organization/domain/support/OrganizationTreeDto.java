package io.funwork.api.organization.domain.support;

import java.util.List;

import lombok.Data;

@Data
public class OrganizationTreeDto {

    private String title;
    private String key;
    private String type;
    private String info;
    private List<OrganizationTreeDto> children;
}
