package com.training.codingstandards;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DatabaseHelper {

    private static final String URL = System.getenv().getOrDefault("HR_DATABASE_URL", "jdbc:mysql://localhost:3306/hr");
    private static final String USER = System.getenv().getOrDefault("HR_DATABASE_USER", "hr_admin");
    private static final String PASSWORD = System.getenv().getOrDefault("HR_DATABASE_PASSWORD", "");

    public Employee findEmployee(String empId) {
        String sql = "SELECT emp_id, name FROM employees WHERE emp_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, empId);
            try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                Employee employee = new Employee();
                employee.empId = rs.getString("emp_id");
                employee.name = rs.getString("name");
                return employee;
            }
            }
        } catch (java.sql.SQLException exception) {
            return null;
        }
        return null;
    }

    public void auditExport(String userInputPath) {
        try (var paths = Files.list(Path.of(userInputPath))) {
            paths.limit(10).forEach(path -> System.out.println(path.getFileName()));
        } catch (IOException | RuntimeException exception) {
            System.err.println("Unable to audit export path");
        }
    }
}
