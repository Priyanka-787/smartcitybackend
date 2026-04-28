package com.klef.fsad.service;

import java.util.List;

import com.klef.fsad.dto.IssueDTO;
import com.klef.fsad.model.Issue;

public interface IssueService {
	String reportIssue(IssueDTO dto);
    List<Issue> getIssuesByUser(int userId);
    List<Issue> getIssuesByCity(int cityId);
    List<Issue> getAllIssues();
    String updateStatus(int id, String status);
    
}