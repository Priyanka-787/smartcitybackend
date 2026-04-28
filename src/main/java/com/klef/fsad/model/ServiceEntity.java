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
@Table(name = "service_table")
public class ServiceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
	private int id;

	@Column(name = "service_name", nullable = false, length = 100)
	private String name;

	@Column(name = "service_type", nullable = false, length = 100)
	private String type;
	// Example: Hospital, Transport, Water, Electricity

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "contact_info", length = 100)
	private String contactInfo;

	@Column(name = "availability_status")
	private String status;
	// ACTIVE / INACTIVE / UNDER_MAINTENANCE
	// 🔗 RELATIONSHIP → Many Services belong to One City
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "ServiceEntity [id=" + id + ", name=" + name + ", type=" + type + ", description=" + description
				+ ", contactInfo=" + contactInfo + ", status=" + status + ", city=" + city + "]";
	}
}