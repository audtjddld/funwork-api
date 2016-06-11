package io.funwork.api.organization.fixture;

import io.funwork.api.organization.domain.Person;
import io.funwork.api.organization.domain.SecurityGrade;

public class PersonFixture {

    private Long id;
    private String email;
    private String passwd;
    private String name;
    private String position;
    private SecurityGrade securityGrade;

    public static PersonFixture anPerson() {
        return new PersonFixture();
    }

    public PersonFixture withId(Long id) {
        this.id = id;
        return this;
    }

    public PersonFixture withEmail(String email) {
        this.email = email;
        return this;
    }

    public PersonFixture withPasswd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    public PersonFixture withName(String name) {
        this.name = name;
        return this;
    }

    public PersonFixture withPosition(String position) {
        this.position = position;
        return this;
    }

    public PersonFixture withSecurityGrade(SecurityGrade securityGrade) {
        this.securityGrade = securityGrade;
        return this;
    }

    public Person build() {
        Person person = new Person();
        person.setId(id);
        person.setEmail(email);
        person.setPasswd(passwd);
        person.setName(name);
        person.setPosition(position);
        person.setSecurityGrade(securityGrade);

        return person;
    }
}
