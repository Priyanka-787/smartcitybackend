package com.klef.fsad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.model.Infrastructure;
import com.klef.fsad.repository.InfrastructureRepository;

@Service
public class InfrastructureServiceImpl implements InfrastructureService {

    @Autowired
    private InfrastructureRepository repository;

    @Override
    public String addInfrastructure(Infrastructure infra) {
        repository.save(infra);
        return "Infrastructure Added";
    }

    @Override
    public List<Infrastructure> getByCity(int cityId) {
        return repository.findByCityId(cityId);
    }

    @Override
    public String updateInfrastructure(Infrastructure infra) {
        repository.save(infra);
        return "Infrastructure Updated";
    }

    @Override
    public String deleteInfrastructure(int id) {
        repository.deleteById(id);
        return "Infrastructure Deleted";
    }
}