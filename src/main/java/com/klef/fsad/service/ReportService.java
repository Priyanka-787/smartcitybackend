package com.klef.fsad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.repository.FeedbackRepository;
import com.klef.fsad.repository.IssueRepository;

@Service
public class ReportService {

    @Autowired
    private IssueRepository issueRepo;
    @Autowired
    private FeedbackRepository feedbackRepo;

    public long getTotalIssues() {
        return issueRepo.count();
    }

    public long getOpenIssues() {
        return issueRepo.countByStatus("OPEN");
    }

    public long getInProgressIssues() {
        return issueRepo.countByStatus("IN PROGRESS");
    }

    public long getResolvedIssues() {
        return issueRepo.countByStatus("RESOLVED");
    }

    public long getFeedbackCount() {
        return feedbackRepo.count();
    }
}
