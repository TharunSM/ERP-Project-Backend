package com.examly.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String projectTitle;
    private String projectDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private String frontEndTechStack;
    private String backendTechStack;
    private String databaseStack;
    private String status;

    public Project() {
    }

    public Project(Long projectId, String projectTitle, String projectDescription, LocalDate startDate,
            LocalDate endDate, String frontEndTechStack, String backendTechStack, String databaseStack, String status) {
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frontEndTechStack = frontEndTechStack;
        this.backendTechStack = backendTechStack;
        this.databaseStack = databaseStack;
        this.status = status;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getFrontEndTechStack() {
        return frontEndTechStack;
    }

    public void setFrontEndTechStack(String frontEndTechStack) {
        this.frontEndTechStack = frontEndTechStack;
    }

    public String getBackendTechStack() {
        return backendTechStack;
    }

    public void setBackendTechStack(String backendTechStack) {
        this.backendTechStack = backendTechStack;
    }

    public String getDatabaseStack() {
        return databaseStack;
    }

    public void setDatabaseStack(String databaseStack) {
        this.databaseStack = databaseStack;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Project [projectId=" + projectId + ", projectTitle=" + projectTitle + ", projectDescription="
                + projectDescription + ", startDate=" + startDate + ", endDate=" + endDate + ", frontEndTechStack="
                + frontEndTechStack + ", backendTechStack=" + backendTechStack + ", databaseStack=" + databaseStack
                + ", status=" + status + "]";
    }
}
