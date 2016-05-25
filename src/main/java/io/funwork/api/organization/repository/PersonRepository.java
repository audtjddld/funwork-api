package io.funwork.api.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import io.funwork.api.organization.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
