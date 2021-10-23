/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Calculators;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author Marti
 */
public class PointCalculatorTest {
    
    public PointCalculatorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of calculateScores method, of class PointCalculator.
     */
    @org.junit.jupiter.api.Test
    public void testCalculateScores() {
        int[][] rounds =  {{7, 1}, {4, 3}, {7, 3}, {10, 0}, {3, 7}, {1, 9}, {0, 8}, {6, 4}, {4, 6}, {5, 5}};
        PointCalculator instance = new PointCalculator();
        int[] expResult = {8, 15, 35, 55, 66, 76, 84, 98, 113, 123};
        int[] result = instance.calculateScores(rounds);
        assertArrayEquals(expResult, result);
    }
    
    @org.junit.jupiter.api.Test
    public void testCalculateScoresWithStrikes() {
        int[][] rounds =  {{10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}};
        PointCalculator instance = new PointCalculator();
        int[] expResult = {30,60,90,120,150,180,210,240,260,270};
        int[] result = instance.calculateScores(rounds);
        assertArrayEquals(expResult, result);
    }
    
    @org.junit.jupiter.api.Test
    public void testCalculateScoresWithSparesAndStrikes() {
        int[][] rounds =  {{4, 6}, {8, 2}, {4, 6}, {0, 10}, {8, 2}, {1, 9}, {10, 0}, {7, 3}, {8, 2}, {10, 0}};
        PointCalculator instance = new PointCalculator();
        int[] expResult = {18, 32, 42, 60, 71, 91, 111, 129, 149, 159};
        int[] result = instance.calculateScores(rounds);
        assertArrayEquals(expResult, result);
    }
    
    @org.junit.jupiter.api.Test
    public void testCalculateScoresWithSparesAndStrikesEndingSpare() {
        int[][] rounds =  {{4, 6}, {8, 2}, {4, 6}, {0, 10}, {8, 2}, {1, 9}, {10, 0}, {7, 3}, {10, 0}, {8, 2}};
        PointCalculator instance = new PointCalculator();
        int[] expResult = {18, 32, 42, 60, 71, 91, 111, 131, 151, 161};
        int[] result = instance.calculateScores(rounds);
        assertArrayEquals(expResult, result);
    }
    
}
