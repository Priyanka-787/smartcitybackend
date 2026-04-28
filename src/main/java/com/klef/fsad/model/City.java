package com.klef.fsad.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "city_table")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int id;

    @Column(name = "city_name", nullable = false, length = 100)
    private String name;

    @Column(name = "state", nullable = false, length = 100)
    private String state;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @Column(name = "population")
    private long population;

    @Column(name = "description", length = 1000)
    private String description;
    private double waterCoverage;
    private double roadNetwork;
    private int hospitals;
    private int schools;
    private int parks;
    private int busRoutes;
    private String area;
    private String stdCode;
    private String pincode;
    private String mayor;
    private String established;
    private String overview;

    // 🔗 RELATIONSHIPS0

    // City → Services
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ServiceEntity> services;

    // City → Infrastructure
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Infrastructure> infrastructures;

    // City → Issues
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Issue> issues;

    // City → Feedback
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Feedback> feedbacks;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ServiceEntity> getServices() {
		return services;
	}

	public void setServices(List<ServiceEntity> services) {
		this.services = services;
	}

	public List<Infrastructure> getInfrastructures() {
		return infrastructures;
	}

	public void setInfrastructures(List<Infrastructure> infrastructures) {
		this.infrastructures = infrastructures;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", state=" + state + ", country=" + country + ", population="
				+ population + ", description=" + description + ", waterCoverage=" + waterCoverage + ", roadNetwork="
				+ roadNetwork + ", hospitals=" + hospitals + ", schools=" + schools + ", parks=" + parks
				+ ", busRoutes=" + busRoutes + ", stdCode=" + stdCode + ", pincode=" + pincode + ", mayor=" + mayor
				+ ", established=" + established + ", overview=" + overview + ", services=" + services
				+ ", infrastructures=" + infrastructures + ", issues=" + issues + ", feedbacks=" + feedbacks + "]";
	}

	public double getWaterCoverage() {
		return waterCoverage;
	}

	public void setWaterCoverage(double waterCoverage) {
		this.waterCoverage = waterCoverage;
	}

	public double getRoadNetwork() {
		return roadNetwork;
	}

	public void setRoadNetwork(double roadNetwork) {
		this.roadNetwork = roadNetwork;
	}

	public int getHospitals() {
		return hospitals;
	}

	public void setHospitals(int hospitals) {
		this.hospitals = hospitals;
	}

	public int getSchools() {
		return schools;
	}

	public void setSchools(int schools) {
		this.schools = schools;
	}

	public int getParks() {
		return parks;
	}

	public void setParks(int parks) {
		this.parks = parks;
	}

	public int getBusRoutes() {
		return busRoutes;
	}

	public void setBusRoutes(int busRoutes) {
		this.busRoutes = busRoutes;
	}

	public String getStdCode() {
		return stdCode;
	}

	public void setStdCode(String stdCode) {
		this.stdCode = stdCode;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getMayor() {
		return mayor;
	}

	public void setMayor(String mayor) {
		this.mayor = mayor;
	}

	public String getEstablished() {
		return established;
	}

	public void setEstablished(String established) {
		this.established = established;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
    
}