package com.klef.fsad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.model.City;
import com.klef.fsad.repository.CityRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public String addCity(City city) {
        cityRepository.save(city);
        return "City Added Successfully";
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityById(int id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    public String updateCity(City city) {
        cityRepository.save(city);
        return "City Updated";
    }

    @Override
    public String deleteCity(int id) {
        cityRepository.deleteById(id);
        return "City Deleted";
    }
    
    @Override
    public City getCityByName(String name) {
        return cityRepository.findByName(name);
    }
}