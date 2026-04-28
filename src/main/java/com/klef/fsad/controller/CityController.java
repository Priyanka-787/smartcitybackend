package com.klef.fsad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.model.City;
import com.klef.fsad.service.CityService;

@RestController
@RequestMapping("/api/city")
@CrossOrigin("*")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/add")
    public ResponseEntity<?> addCity(@RequestBody City city) {
        return ResponseEntity.ok(cityService.addCity(city));
    }

    @GetMapping("/all")
    public ResponseEntity<List<City>> getAll() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCity(@RequestBody City city) {
        return ResponseEntity.ok(cityService.updateCity(city));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable int id) {
        return ResponseEntity.ok(cityService.deleteCity(id));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<City> getCity(@PathVariable int id) {
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<City> getCityByName(@PathVariable String name) {
        return ResponseEntity.ok(cityService.getCityByName(name));
    }
}