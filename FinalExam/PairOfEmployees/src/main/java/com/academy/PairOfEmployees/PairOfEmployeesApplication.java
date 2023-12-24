package com.academy.PairOfEmployees;

import com.academy.PairOfEmployees.models.Employee;
import com.academy.PairOfEmployees.services.EmployeeService;
import com.academy.PairOfEmployees.operationsWithFiles.CSVReader;
import com.academy.PairOfEmployees.services.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PairOfEmployeesApplication implements CommandLineRunner {

	@Autowired
	private WorkService workService;

	@Autowired
	private EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(PairOfEmployeesApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// Read input from CSV file and insert into the database
		String inputFilePath = "src\\main\\java\\com\\academy\\PairOfEmployees\\input.csv";
		List<Employee> employees = readEmployeesFromCSV(inputFilePath);

		for (Employee employee : employees) {
			employeeService.saveEmployee(employee);
		}

		// Calculate duration and write to output file
		String outputFilePath = "src\\main\\java\\com\\academy\\PairOfEmployees\\output.csv";
		List<String> result = workService.calculateDurationAndWriteToFile(outputFilePath);
	}

	private List<Employee> readEmployeesFromCSV(String filePath) {
		CSVReader csvReader = new CSVReader();
		return csvReader.read(filePath);
	}
}
