package com.academy.PairOfEmployees.controllers;

import com.academy.PairOfEmployees.models.Employee;
import com.academy.PairOfEmployees.services.EmployeeService;
import com.academy.PairOfEmployees.services.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    private final WorkService workService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, WorkService workService) {
        this.employeeService = employeeService;
        this.workService = workService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.saveEmployee(employee);

        updateInputFile(createdEmployee);

        String outputFilePath = "src\\main\\java\\com\\academy\\PairOfEmployees\\output.csv";
        List<String> result = workService.calculateDurationAndWriteToFile(outputFilePath);

        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    private void updateInputFile(Employee newEmployee) {
        String inputFilePath = "src\\main\\java\\com\\academy\\PairOfEmployees\\input.csv";

        try (FileWriter writer = new FileWriter(inputFilePath, true)) {
            // Append the new employee data to the input file
            writer.write(newEmployee.getId() + "," +
                    newEmployee.getProjectId() + "," +
                    newEmployee.getStartDate() + "," +
                    (newEmployee.getEndDate() != null ? newEmployee.getEndDate() : "NULL") +
                    "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId, @RequestBody Employee updatedEmployee) {
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);
        if (existingEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingEmployee.setProjectId(updatedEmployee.getProjectId());
        existingEmployee.setStartDate(updatedEmployee.getStartDate());
        existingEmployee.setEndDate(updatedEmployee.getEndDate());

        Employee savedEmployee = employeeService.saveEmployee(existingEmployee);


        String outputFile = "src\\main\\java\\com\\academy\\PairOfEmployees\\input.csv";
        List<Employee> employeesUpdated = employeeService.writeEmployeesToFile(outputFile);

        String outputFilePath = "src\\main\\java\\com\\academy\\PairOfEmployees\\output.csv";
        List<String> result = workService.calculateDurationAndWriteToFile(outputFilePath);

        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int employeeId) {
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);
        if (existingEmployee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employeeService.deleteEmployee(employeeId);

        String outputFile = "src\\main\\java\\com\\academy\\PairOfEmployees\\input.csv";
        List<Employee> employeesUpdated = employeeService.writeEmployeesToFile(outputFile);

        String outputFilePath = "src\\main\\java\\com\\academy\\PairOfEmployees\\output.csv";
        List<String> result = workService.calculateDurationAndWriteToFile(outputFilePath);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}