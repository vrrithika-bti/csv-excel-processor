package com.training.codingstandards;

import java.util.ArrayList;
import java.util.List;

public class ReportConfig {

    private static final List<Employee> CACHE = new ArrayList<>();

    public static final String OUTPUT_SHEET = "Payroll";

    private ReportConfig() {
    }

    public static void cache(Employee employee) {
        CACHE.add(employee);
    }
}
