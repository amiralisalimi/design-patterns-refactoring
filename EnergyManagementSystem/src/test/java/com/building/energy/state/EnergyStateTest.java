package com.building.energy.state;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnergyStateTest {

    @Test
    void testActiveStateConsumption() {
        EnergyState state = new ActiveState();
        assertEquals(100, state.getEnergyConsumption(), "Active should consume 100 units");
        assertEquals("System is fully active.", state.getStatus());
    }

    @Test
    void testEcoStateConsumption() {
        EnergyState state = new EcoState();
        assertEquals(40, state.getEnergyConsumption(), "Eco should consume 40 units");
        assertEquals("System is in eco mode.", state.getStatus());
    }

    @Test
    void testShutdownStateConsumption() {
        EnergyState state = new ShutdownState();
        assertEquals(0, state.getEnergyConsumption(), "Shutdown should consume 0 units");
        assertEquals("System is shut down.", state.getStatus());
    }
}
