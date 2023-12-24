package com.academy.PairOfEmployees.operationsWithFiles;

import com.academy.PairOfEmployees.models.Employee;

import java.util.List;

// interface for the reader class
public interface CustomReader {
    public List<Employee> read(String filename);
}
