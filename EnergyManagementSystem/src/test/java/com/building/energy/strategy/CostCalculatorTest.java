package com.building.energy.strategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CostCalculatorTest {

    @Test
    void testCalculatorWithStandardStrategy() {
        CostCalculator calc = new CostCalculator(new StandardCostStrategy());
        assertEquals(5000, calc.calculate(10));
    }

    @Test
    void testChangeStrategyAtRuntime() {
        CostCalculator calc = new CostCalculator(new StandardCostStrategy());
        assertEquals(5000, calc.calculate(10));

        calc.setStrategy(new PeakCostStrategy());
        assertEquals(10000, calc.calculate(10));

        calc.setStrategy(new GreenCostStrategy());
        assertEquals(3000, calc.calculate(10));
    }
}
