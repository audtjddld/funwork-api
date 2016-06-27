package io.funwork.api.sns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import io.funwork.api.sns.domain.CommentSns;

@Repository
public interface CommentSnsRepository extends JpaRepository<CommentSns, Long> {
  List<CommentSns> findBySnsId(Long snsId);
}
