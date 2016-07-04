package io.funwork.api.sns.repository;

import io.funwork.api.sns.domain.Sns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnsRepository extends JpaRepository<Sns, Long> {
}
