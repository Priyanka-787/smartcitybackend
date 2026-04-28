package com.klef.fsad.service;

import java.util.List;
import com.klef.fsad.model.Infrastructure;

public interface InfrastructureService {

    String addInfrastructure(Infrastructure infra);

    List<Infrastructure> getByCity(int cityId);

    String updateInfrastructure(Infrastructure infra);

    String deleteInfrastructure(int id);
}