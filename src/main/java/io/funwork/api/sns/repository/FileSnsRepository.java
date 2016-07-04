package io.funwork.api.sns.repository;

import io.funwork.api.sns.domain.FileSns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileSnsRepository extends JpaRepository<FileSns, Long> {
}
