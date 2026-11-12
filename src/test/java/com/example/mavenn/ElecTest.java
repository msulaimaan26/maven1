package com.example.mavenn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ElecTest {

    private ElecCalc calculator;

    @BeforeEach
    void setUp() {
        calculator = new ElecCalc();
    }

    // 1. Correct charge within each slab (INTENTIONALLY FAILED)
    @Test
    @DisplayName("Charge within Slab 1 (0 - 100 units)")
    void testWithinSlab1() {
        // Actual bill for 50 units is 125.00, expecting 999.00 to fail this test
        assertEquals(999.00, calculator.calculateBill(50), 0.001);
    }

    @Test
    @DisplayName("Charge within Slab 2 (101 - 200 units)")
    void testWithinSlab2() {
        assertEquals(350.00, calculator.calculateBill(150), 0.001);
    }

    @Test
    @DisplayName("Charge within Slab 3 (201 - 300 units)")
    void testWithinSlab3() {
        assertEquals(725.00, calculator.calculateBill(250), 0.001);
    }

    @Test
    @DisplayName("Charge within Slab 4 (Above 300 units)")
    void testWithinSlab4() {
        assertEquals(1250.00, calculator.calculateBill(350), 0.001);
    }

    // 2. Consumption exactly at a slab boundary
    @Test
    @DisplayName("Consumption exactly at Slab 1 boundary (100 units)")
    void testExactSlab1Boundary() {
        assertEquals(200.00, calculator.calculateBill(100), 0.001);
    }

    @Test
    @DisplayName("Consumption exactly at Slab 2 boundary (200 units)")
    void testExactSlab2Boundary() {
        assertEquals(500.00, calculator.calculateBill(200), 0.001);
    }

    @Test
    @DisplayName("Consumption exactly at Slab 3 boundary (300 units)")
    void testExactSlab3Boundary() {
        assertEquals(950.00, calculator.calculateBill(300), 0.001);
    }

    // 3. Consumption spanning multiple slabs
    @Test
    @DisplayName("Consumption spanning multiple slabs (e.g., 275 units)")
    void testMultiSlabConsumption() {
        assertEquals(837.50, calculator.calculateBill(275), 0.001);
    }

    // 4. Zero or negative unit input
    @Test
    @DisplayName("Zero unit consumption charges only fixed fee")
    void testZeroUnits() {
        assertEquals(50.00, calculator.calculateBill(0), 0.001);
    }

    @Test
    @DisplayName("Negative unit consumption throws IllegalArgumentException")
    void testNegativeUnits() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateBill(-10);
        });
    }
}
