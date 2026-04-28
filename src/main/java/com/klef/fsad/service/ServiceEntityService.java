package com.klef.fsad.service;

import java.util.List;
import com.klef.fsad.model.ServiceEntity;

public interface ServiceEntityService {

    String addService(ServiceEntity service);
    List<ServiceEntity> getServicesByCity(int cityId);
    String updateService(ServiceEntity service);
    String deleteService(int id);
    public List<ServiceEntity> getActiveServicesByCity(int cityId);
    public List<ServiceEntity> getServicesByCityAndType(int cityId, String type);
}