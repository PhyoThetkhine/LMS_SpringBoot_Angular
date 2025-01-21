package com.system;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotParameterizedTest {
    public static NotParameterized p;
    @BeforeAll
    public static void setup() {
        p = new NotParameterized();
    }
    @Test
    public void testAddPositiveNumbers() {
        System.out.println("Adding Positive Numbers : ");
        int result = p.sum(2, 3);
        assertEquals(5, result);
    }
    @Test
    public void testAddNegativeNumbers() {
        System.out.println("Adding Negative Numbers : ");
        int result = p.sum(-2, -3);
        assertEquals(-5, result);
    }
    @Test
    public void testAddZero() {
        System.out.println("Adding Zero Numbers : ");
        int result = p.sum(0, 0);
        assertEquals(0, result);
    }
}