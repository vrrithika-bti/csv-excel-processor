package com.training.codingstandards;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeProcessorTest {

    @Test
    void processCreatesPayrollRowForEachEmployee() {
        Employee employee = new Employee("1001", "Asha Raman", "asha.raman@example.com",
                "Engineering", 92000, 6, "IN", "lead.eng@example.com");

        EmployeeProcessor processor = new EmployeeProcessor();
        List<EmployeeProcessor.PayrollRow> rows = processor.process(Arrays.asList(employee));

        assertNotNull(rows);
        assertEquals(1, rows.size());
        assertEquals("1001", rows.get(0).empId);
        assertEquals("Engineering", rows.get(0).department);
        assertTrue(rows.get(0).bonus > 0);
    }

    @Test
    void processHandlesNullAndDistinctStringValues() {
        Employee employee = new Employee(new String("1002"), "Ben Carter", "ben@example.com",
                new String("Finance"), 75000, 2, new String("US"), "manager@example.com");

        EmployeeProcessor processor = new EmployeeProcessor();

        assertTrue(processor.process(null).isEmpty());
        List<EmployeeProcessor.PayrollRow> rows = processor.process(List.of(employee));

        assertEquals("Finance", rows.get(0).department);
        assertTrue(rows.get(0).tax > 0);
    }
}
