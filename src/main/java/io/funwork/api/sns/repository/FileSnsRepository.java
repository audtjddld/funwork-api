package io.funwork.api.sns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.funwork.api.sns.domain.FileSns;

@Repository
public interface FileSnsRepository extends JpaRepository<FileSns, Long> {
}
