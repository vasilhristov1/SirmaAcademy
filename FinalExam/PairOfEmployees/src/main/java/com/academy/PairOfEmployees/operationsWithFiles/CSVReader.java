package com.academy.PairOfEmployees.operationsWithFiles;

import com.academy.PairOfEmployees.models.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// class to read CSV files
public class CSVReader implements CustomReader {
    @Override
    public List<Employee> read(String filename) {
        List<Employee> employees = new ArrayList<>(); // list with employees
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // if there are headers we skip them
                if (checkForHeaders(line)) {
                    continue;
                }

                Employee employee = fromCSV(line);
                employees.add(employee); // adding the employee to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public static Employee fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(values[0]));
        employee.setProjectId(Integer.parseInt(values[1]));

        LocalDate startDate = parseDate(values[2]);
        LocalDate endDate = values[3].equalsIgnoreCase("NULL") ? LocalDate.now() : parseDate(values[3]);

        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("End date must be after the start date");
        }

        employee.setStartDate(startDate);
        employee.setEndDate(endDate);

        return employee;
    }

    private static LocalDate parseDate(String date) {
        List<String> dateFormats = Arrays.asList(
                "yyyy-MM-dd",
                "MM/dd/yyyy",
                "dd-MM-yyyy",
                "yyyy/MM/dd",
                "dd/MM/yyyy",
                "yyyyMMdd",
                "dd-MMM-yyyy",
                "MMM dd, yyyy",
                "yyyy.MM.dd");
        for (String format : dateFormats) {
            try {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new IllegalArgumentException("Invalid date format: " + date);
    }

    private static boolean checkForHeaders(String line) {
        if (line.equals("EmpId,ProjectId,DateFrom,DateTo")) {
            return true;
        } else {
            return false;
        }
    }
}
