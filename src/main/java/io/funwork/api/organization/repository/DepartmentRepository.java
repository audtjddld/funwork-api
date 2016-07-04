package io.funwork.api.organization.repository;

import io.funwork.api.organization.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
