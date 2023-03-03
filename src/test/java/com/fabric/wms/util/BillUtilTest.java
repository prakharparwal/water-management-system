package com.fabric.wms.util;

import com.fabric.wms.pricing.Price;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillUtilTest {


    @Test
    void shouldCalculateAndReturnBorewellWaterCostBasedOnConsumption() {
        int consumption = 500;
        int expectedAmount = 750;
        int actualAmount = BillUtil.getBorewellWaterAmount(consumption);
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void shouldReturnTankerWaterConsumptionCostForGuestsUsage() {
        int guestsConsumption = 3500;
        int expectedCost = 15500;
        int actualCost = BillUtil.getTankerConsumptionCost(guestsConsumption);
        assertEquals(expectedCost, actualCost);
    }

    @Test
    void shouldReturnTankerWaterConsumptionCostForGuestsUsageHavingConsumptionLessThan500() {
        int guestsConsumption = 200;
        int expectedCost = 400;
        int actualCost = BillUtil.getTankerConsumptionCost(guestsConsumption);
        assertEquals(expectedCost, actualCost);
    }


    @Test
    void shouldReturnTankerWaterConsumptionCostForGuestsUsageHavingConsumptionLessThan1500AndGreaterThan500() {
        int guestsConsumption = 700;
        int expectedCost = 1600;
        int actualCost = BillUtil.getTankerConsumptionCost(guestsConsumption);
        assertEquals(expectedCost, actualCost);
    }

}