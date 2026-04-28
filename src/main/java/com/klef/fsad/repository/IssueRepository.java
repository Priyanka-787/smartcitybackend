package com.klef.fsad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

    List<Issue> findByUserId(int userId);
    List<Issue> findByCityId(int cityId);
    List<Issue> findByStatus(String status);
    long countByStatusNot(String status);
    long countByStatus(String status);
}