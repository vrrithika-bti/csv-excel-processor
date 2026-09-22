package com.training.codingstandards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EmployeeTest {

    @Test
    void employeesWithSameIdAreEqual() {
        Employee first = new Employee(new String("1001"), "Asha", "asha@example.com",
                "Engineering", 92000, 6, "IN");
        Employee second = new Employee(new String("1001"), "Different", "different@example.com",
                "Finance", 75000, 2, "US");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void employeesWithDifferentIdsAreNotEqual() {
        Employee first = new Employee("1001", "Asha", "asha@example.com",
                "Engineering", 92000, 6, "IN");
        Employee second = new Employee("1002", "Ben", "ben@example.com",
                "Finance", 75000, 2, "US");

        assertNotEquals(first, second);
    }
}