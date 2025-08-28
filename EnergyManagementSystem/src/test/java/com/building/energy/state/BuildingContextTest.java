package com.building.energy.state;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BuildingContextTest {

    @Test
    void testDefaultStateIsActive() {
        BuildingContext context = new BuildingContext();
        assertEquals("System is fully active.", context.getCurrentState().getStatus());
    }

    @Test
    void testChangeStateToEco() {
        BuildingContext context = new BuildingContext();
        context.setState(new EcoState());
        assertEquals("System is in eco mode.", context.getCurrentState().getStatus());
    }

    @Test
    void testChangeStateToShutdown() {
        BuildingContext context = new BuildingContext();
        context.setState(new ShutdownState());
        assertEquals("System is shut down.", context.getCurrentState().getStatus());
    }
}
