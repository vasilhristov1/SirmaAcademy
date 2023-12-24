package com.academy.PairOfEmployees.services;

import com.academy.PairOfEmployees.WorkCalculator;
import com.academy.PairOfEmployees.models.Employee;
import com.academy.PairOfEmployees.operationsWithFiles.CSVWriter;
import com.academy.PairOfEmployees.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return employee.orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public List<Employee> writeEmployeesToFile(String outputFile) {
        List<Employee> employees = employeeRepository.findAll();

        CSVWriter writer = new CSVWriter();
        writer.write(employees, outputFile);

        return employees;
    }
}
