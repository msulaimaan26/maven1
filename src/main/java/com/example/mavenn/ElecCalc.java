package com.example.mavenn;

import java.util.Scanner;

public class ElecCalc {

    // Predefined Tariff Slabs and Rates (per unit)
    // Slab 1: 0 - 100 units @ 1.50
    // Slab 2: 101 - 200 units @ 3.00
    // Slab 3: 201 - 300 units @ 4.50
    // Slab 4: Above 300 units @ 6.00
    private static final double SLAB_1_RATE = 1.50;
    private static final double SLAB_2_RATE = 3.00;
    private static final double SLAB_3_RATE = 4.50;
    private static final double SLAB_4_RATE = 6.00;

    // Fixed monthly meter/service charge
    public static final double FIXED_CHARGE = 50.00;

    /**
     * Calculates the total electricity bill based on units consumed.
     *
     * @param units Number of electricity units consumed.
     * @return Total payable bill amount including fixed charges.
     * @throws IllegalArgumentException if units are negative.
     */
    public double calculateBill(double units) {
        if (units < 0) {
            throw new IllegalArgumentException("Consumed units cannot be negative.");
        }

        if (units == 0) {
            return FIXED_CHARGE;
        }

        double energyCharge = 0.0;

        if (units <= 100) {
            energyCharge = units * SLAB_1_RATE;
        } else if (units <= 200) {
            energyCharge = (100 * SLAB_1_RATE) + ((units - 100) * SLAB_2_RATE);
        } else if (units <= 300) {
            energyCharge = (100 * SLAB_1_RATE) + (100 * SLAB_2_RATE) + ((units - 200) * SLAB_3_RATE);
        } else {
            energyCharge = (100 * SLAB_1_RATE) + (100 * SLAB_2_RATE) + (100 * SLAB_3_RATE)
                    + ((units - 300) * SLAB_4_RATE);
        }

        return energyCharge + FIXED_CHARGE;
    }

    public static void main(String[] args) {
        ElecCalc calculator = new ElecCalc();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Electricity Bill Calculator ===");
        System.out.print("Enter electricity units consumed: ");

        if (scanner.hasNextDouble()) {
            double units = scanner.nextDouble();
            try {
                double totalBill = calculator.calculateBill(units);
                System.out.printf("Units Consumed : %.2f%n", units);
                System.out.printf("Fixed Charges  : %.2f%n", FIXED_CHARGE);
                System.out.printf("Total Bill     : %.2f%n", totalBill);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid input. Please enter a numeric value.");
        }

        scanner.close();
    }
}
