package io.funwork.api.organization.fixture;

import io.funwork.api.organization.domain.Department;

public class DepartmentFixture {

    private String name;
    private Department parentDept;
    private String useYn;

    public static DepartmentFixture anDepartment() {
        return new DepartmentFixture();
    }

    public DepartmentFixture withName(String name) {
        this.name = name;
        return this;
    }

    public DepartmentFixture withParentDept(Department parentDept) {
        this.parentDept = parentDept;
        return this;
    }

    public DepartmentFixture withUseYn(String useYn) {
        this.useYn = useYn;
        return this;
    }

    public Department build() {
        Department department = new Department();
        department.setName(this.name);
        department.setParentDept(this.parentDept);
        department.setUseYn(this.useYn);
        return department;
    }
}
