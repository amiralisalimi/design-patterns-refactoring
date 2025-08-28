package com.building.energy.strategy;

public class CostCalculator {
    private CostStrategy strategy;

    public CostCalculator(CostStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(CostStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculate(int units) {
        return strategy.calculateCost(units);
    }
}
