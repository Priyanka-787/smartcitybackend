package com.klef.fsad.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.dto.IssueDTO;
import com.klef.fsad.model.City;
import com.klef.fsad.model.Issue;
import com.klef.fsad.model.User;
import com.klef.fsad.repository.CityRepository;
import com.klef.fsad.repository.IssueRepository;
import com.klef.fsad.repository.UserRepository;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository repository;
    @Autowired
    private CityRepository cityRepo;
    @Autowired
    private UserRepository userRepo;

    @Override
    public String reportIssue(IssueDTO dto) {

        Issue issue = new Issue();

        issue.setTitle(dto.getTitle());
        issue.setDescription(dto.getDescription());
        issue.setLocation(dto.getLocation());
        issue.setPriority(dto.getPriority());

        issue.setStatus("OPEN");
        issue.setCreatedAt(LocalDateTime.now());

        City city = cityRepo.findByName(dto.getCityName());
        if (city == null) {
            return "City Not Found";
        }
        issue.setCity(city);

        User user = userRepo.findById(dto.getUserId()).orElse(null);
        issue.setUser(user);

        repository.save(issue);

        return "Issue Reported Successfully";
    }

    @Override
    public List<Issue> getIssuesByUser(int userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Issue> getIssuesByCity(int cityId) {
        return repository.findByCityId(cityId);
    }

    @Override
    public List<Issue> getAllIssues() {
        return repository.findAll();
    }

    @Override
    public String updateStatus(int id, String status) {

        Issue issue = repository.findById(id).orElse(null);

        if (issue == null) return "Issue Not Found";

        issue.setStatus(status);
        issue.setUpdatedAt(LocalDateTime.now());

        repository.save(issue);

        return "Status Updated";
    }
}