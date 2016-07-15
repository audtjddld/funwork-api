package io.funwork.api.organization.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.funwork.api.organization.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
