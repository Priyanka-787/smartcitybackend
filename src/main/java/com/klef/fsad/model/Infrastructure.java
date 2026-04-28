package com.klef.fsad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "infrastructure_table")
public class Infrastructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "infra_id")
    private int id;

    @Column(name = "infra_name", nullable = false, length = 100)
    private String name;

    @Column(name = "infra_type", nullable = false, length = 100)
    private String type;
    // Example: Road, Bridge, Park, Building
    @Column(name = "status", nullable = false)
    private String status;
    // GOOD / NEEDS_REPAIR / UNDER_CONSTRUCTION
    @Column(name = "location", length = 200)
    private String location;
    @Column(name = "description", length = 500)
    private String description;

    // 🔗 RELATIONSHIP → Many Infrastructure belong to One City
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @JsonIgnore
    private City city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Infrastructure [id=" + id + ", name=" + name + ", type=" + type + ", status=" + status + ", location="
				+ location + ", description=" + description + ", city=" + city + "]";
	}
	
}