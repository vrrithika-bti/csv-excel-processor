package com.training.codingstandards;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReportConfig {

    public static final List<Employee> CACHE = new ArrayList<>();

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String OUTPUT_SHEET = "Payroll";

    private ReportConfig() {
    }
}
