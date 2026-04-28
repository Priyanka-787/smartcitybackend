package com.klef.fsad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.model.ServiceEntity;
import com.klef.fsad.service.ServiceEntityService;

@RestController
@RequestMapping("/api/services")
@CrossOrigin("*")
public class ServiceEntityController {

	@Autowired
	private ServiceEntityService service;

	@PostMapping
	public ResponseEntity<?> add(@RequestBody ServiceEntity s) {
		return ResponseEntity.ok(service.addService(s));
	}

	@GetMapping("/city/{cityId}")
	public ResponseEntity<List<ServiceEntity>> getByCity(@PathVariable int cityId) {
		return ResponseEntity.ok(service.getServicesByCity(cityId));
	}

	@GetMapping("/city/{cityId}/active")
	public ResponseEntity<List<ServiceEntity>> getActive(@PathVariable int cityId) {
		return ResponseEntity.ok(service.getActiveServicesByCity(cityId));
	}

	@GetMapping("/city/{cityId}/type/{type}")
	public ResponseEntity<List<ServiceEntity>> getByType(@PathVariable int cityId, @PathVariable String type) {
		return ResponseEntity.ok(service.getServicesByCityAndType(cityId, type));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		return ResponseEntity.ok(service.deleteService(id));
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody ServiceEntity s) {
	    return ResponseEntity.ok(service.updateService(s));
	}
}