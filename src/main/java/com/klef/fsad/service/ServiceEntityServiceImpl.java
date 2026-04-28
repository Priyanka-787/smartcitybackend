package com.klef.fsad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.model.ServiceEntity;
import com.klef.fsad.repository.ServiceEntityRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceEntityServiceImpl implements ServiceEntityService {

    @Autowired
    private ServiceEntityRepository repository;

    @Override
    public String addService(ServiceEntity service) {
        repository.save(service);
        return "Service Added";
    }

    @Override
    public List<ServiceEntity> getServicesByCity(int cityId) {
        return repository.findByCityId(cityId);
    }

    @Override
    public String updateService(ServiceEntity updatedService) {
        // ✅ Fetch existing entity first, then update only changed fields
        ServiceEntity existing = repository.findById(updatedService.getId()).orElse(null);
        if (existing == null) return "Service Not Found";

        existing.setName(updatedService.getName());
        existing.setType(updatedService.getType());
        existing.setDescription(updatedService.getDescription());
        existing.setContactInfo(updatedService.getContactInfo());
        existing.setStatus(updatedService.getStatus());
        // ✅ Don't touch city — keep the existing relationship

        repository.save(existing);
        return "Service Updated";
    }
    
    @Override
    public String deleteService(int id) {
        repository.deleteById(id);
        return "Service Deleted";
    }
    
    @Override
    public List<ServiceEntity> getActiveServicesByCity(int cityId) {
        return repository.findByCityIdAndStatus(cityId, "ACTIVE");
    }

    @Override
    public List<ServiceEntity> getServicesByCityAndType(int cityId, String type) {
        return repository.findByCityIdAndType(cityId, type);
    }
}