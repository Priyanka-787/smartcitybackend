package com.klef.fsad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.model.Infrastructure;

@Repository
public interface InfrastructureRepository extends JpaRepository<Infrastructure, Integer> {

    List<Infrastructure> findByCityId(int cityId);
    long countByType(String type);
}