package com.training.codingstandards;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvEmployeeReader {

    public List<Employee> read(String csvPath) {
        List<Employee> employees = new ArrayList<>();
        try (InputStream inputStream = openInputStream(csvPath);
             InputStreamReader inputReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             CSVParser parser = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build().parse(inputReader)) {
                for (CSVRecord employeeRecord : parser) {
                Employee employee = new Employee(
                    employeeRecord.get("empId"),
                    employeeRecord.get("name"),
                    employeeRecord.get("email"),
                    employeeRecord.get("department"),
                    Double.parseDouble(employeeRecord.get("salary")),
                    Integer.parseInt(employeeRecord.get("yearsOfService")),
                    employeeRecord.get("country"));
                employee.setManagerEmail(employeeRecord.get("managerEmail"));
                employees.add(employee);
                ReportConfig.cache(employee);
            }
        } catch (IOException | IllegalArgumentException exception) {
            throw new IllegalStateException("Unable to read employee CSV", exception);
        }
        return employees;
    }

    private InputStream openInputStream(String csvPath) throws IOException {
        InputStream inputStream;
            if (csvPath == null) {
                inputStream = CsvEmployeeReader.class.getResourceAsStream("/employees.csv");
                if (inputStream == null) {
                    throw new IOException("Bundled employees.csv was not found");
                }
            } else {
                inputStream = new FileInputStream(csvPath);
            }
        return inputStream;
    }
}
