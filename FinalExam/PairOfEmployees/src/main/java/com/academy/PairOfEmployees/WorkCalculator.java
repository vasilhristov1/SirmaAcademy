package com.academy.PairOfEmployees;

import com.academy.PairOfEmployees.models.Employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class WorkCalculator {
    public static List<String> calculateDuration(List<Employee> employees) {
        List<String> pairs = new ArrayList<>();

        for (int i = 0; i < employees.size(); i++) {
            Employee employee1 = employees.get(i);

            for (int j = i + 1; j < employees.size(); j++) {
                Employee employee2 = employees.get(j);

                if (employee1.getProjectId() == employee2.getProjectId()) {
                    long overlapDays = calculateOverlapDays(employee1.getStartDate(), employee1.getEndDate(), employee2.getStartDate(), employee2.getEndDate());

                    if (overlapDays > 0) {
                        String pairKey = employee1.getProjectId() + ", " + employee1.getId() + ", " + employee2.getId() + ", " + overlapDays + "\n";
                        pairs.add(pairKey);
                    }
                }
            }
        }

        return pairs;
    }

    private static long calculateOverlapDays (LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2) {
        LocalDate latestStartDate = startDate1.isAfter(startDate2) ? startDate1 : startDate2;
        LocalDate earliestEndDate = endDate1.isBefore(endDate2) ? endDate1 : endDate2;

        if (latestStartDate.isBefore(earliestEndDate) || latestStartDate.isEqual(earliestEndDate)) {
            return ChronoUnit.DAYS.between(latestStartDate, earliestEndDate) + 1;
        } else {
            return 0;
        }
    }
}
