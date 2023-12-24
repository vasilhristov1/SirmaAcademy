package com.academy.PairOfEmployees.services;

import com.academy.PairOfEmployees.WorkCalculator;
import com.academy.PairOfEmployees.models.Employee;
import com.academy.PairOfEmployees.operationsWithFiles.CSVWriter;
import com.academy.PairOfEmployees.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<String> calculateDurationAndWriteToFile(String outputFile) {
        List<Employee> employees = employeeRepository.findAll();

        List<String> result = WorkCalculator.calculateDuration(employees);
        CSVWriter writer = new CSVWriter();
        writer.write(result, outputFile);

        return result;
    }
}
