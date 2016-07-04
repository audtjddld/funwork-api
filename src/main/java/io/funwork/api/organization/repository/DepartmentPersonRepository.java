package io.funwork.api.organization.repository;

import io.funwork.api.organization.domain.DepartmentPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentPersonRepository extends JpaRepository<DepartmentPerson, Long> {
}
