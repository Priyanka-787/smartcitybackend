package com.klef.fsad.service;

import java.util.List;
import com.klef.fsad.model.City;

public interface CityService {

	String addCity(City city);
	List<City> getAllCities();
	City getCityById(int id);
	String updateCity(City city);
	String deleteCity(int id);
	City getCityByName(String name);
}