package io.funwork.api.organization.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* 사용자 이메일 */
    private String email;

    /* 사용자 암호 */
    @NotNull
    @Size(min = 8, max = 20)
    private String passwd;

    /* 사용자 이름 */
    @NotNull
    private String name;

    /* 사용자 직책 */
    private String position;

    /* 사용자 보안등급 */
    @NotNull
    private SecurityGrade securityGrade = SecurityGrade.NORMAL;

    @OneToMany(mappedBy = "person")
    private List<DepartmentPerson> departmentPersons = new ArrayList<>();

}