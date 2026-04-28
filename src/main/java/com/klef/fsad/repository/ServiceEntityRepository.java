package com.klef.fsad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.model.ServiceEntity;

@Repository
public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, Integer> {

	List<ServiceEntity> findByCityId(int cityId);
	long countByType(String type);
	List<ServiceEntity> findByCityIdAndType(int cityId, String type);
	List<ServiceEntity> findByCityIdAndStatus(int cityId, String status);
}