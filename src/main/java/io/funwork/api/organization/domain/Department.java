package io.funwork.api.organization.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    @NotNull
    public String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonManagedReference
    public Department parentDept;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Set<Department> childDept = new HashSet<>();

    @NotNull
    private String useYn = "N";

    @OneToMany(mappedBy = "department", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private List<DepartmentPerson> departmentPersons = new ArrayList<>();

    /**
     * 자식부서 추가 및 부모부서로 등록.
     *
     * @param department 부모부서
     */
    public void addParentDept(Department department) {
        if (isNotExistChildDepartment(department)) {
            this.parentDept = department;
            department.getChildDept().add(this);
        }
    }

    private boolean isNotExistChildDepartment(Department department) {
        return department != null && !department.getChildDept().contains(this);
    }
}