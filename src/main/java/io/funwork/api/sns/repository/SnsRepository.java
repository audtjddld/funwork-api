package io.funwork.api.sns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import io.funwork.api.sns.domain.Sns;

@Repository
public interface SnsRepository extends JpaRepository<Sns, Long> {

  List<Sns> findByPersonId(String personId);

}
