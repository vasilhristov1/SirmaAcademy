package com.academy.PairOfEmployees.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    private int id;
    private int projectId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Employee() {}

    public Employee(int id, int projectId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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

    @Override
    public String toString() {
        return this.id + "," + this.projectId + "," + this.startDate + "," + this.endDate + "\n";
    }
}
