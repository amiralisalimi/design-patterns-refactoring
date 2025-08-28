package com.building.energy.strategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CostStrategyTest {

    @Test
    void testStandardCostStrategy() {
        CostStrategy strategy = new StandardCostStrategy();
        assertEquals(5000, strategy.calculateCost(10), "Standard should be 500 per unit");
    }

    @Test
    void testPeakCostStrategy() {
        CostStrategy strategy = new PeakCostStrategy();
        assertEquals(10000, strategy.calculateCost(10), "Peak should be 1000 per unit");
    }

    @Test
    void testGreenCostStrategy() {
        CostStrategy strategy = new GreenCostStrategy();
        assertEquals(3000, strategy.calculateCost(10), "Green should be 300 per unit");
    }
}
