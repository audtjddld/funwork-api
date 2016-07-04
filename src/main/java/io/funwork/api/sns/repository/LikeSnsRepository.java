package io.funwork.api.sns.repository;

import io.funwork.api.sns.domain.LikeSns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeSnsRepository extends JpaRepository<LikeSns, Long> {
}
