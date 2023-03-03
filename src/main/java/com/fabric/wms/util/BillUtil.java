package com.fabric.wms.util;

import com.fabric.wms.pricing.Price;

public class BillUtil {

    public static int[] getDistribution(String distributionRatio, int totalBaseConsumption) {
        String[] ratio = distributionRatio.split(":");
        int sumRatio = 0;
        for (int i = 0; i < ratio.length; i++) {
            sumRatio += Integer.parseInt(ratio[i]);
        }
        int[] distribution = new int[ratio.length];
        int remaining = totalBaseConsumption;
        for (int i = 0; i < ratio.length; i++) {
            distribution[i] = Math.round((float) Integer.parseInt(ratio[i]) / sumRatio * totalBaseConsumption);
            remaining -= distribution[i];
        }
        while (remaining > 0) {
            for (int i = 0; i < ratio.length && remaining > 0; i++) {
                distribution[i]++;
                remaining--;
            }
        }
        return distribution;
    }


    public static int getCorporationWaterAmount(int consumption) {
        return consumption* Price.CORPORATION_WATER_COST;
    }

    public static int getBorewellWaterAmount(int consumption) {
        return (int) Math.round(consumption*Price.BOREWELL_WATER_COST);
    }


    /*
    * We can write this method in better way. Right now with the time constraint I am having
    * Just using the basic way.
    * There is a scope of improvement here.
    * */
    public static int getTankerConsumptionCost(int guestsConsumption) {
        int cost = 0;
        int slabTwoCount = 0;
        int slabOneCount = 0;
        int slabThreeCount = 0;
        int slabFourCount = 0;


        if(guestsConsumption<=500) {
            slabOneCount = guestsConsumption;
        } else
        {
            slabOneCount = 500;
        }

        if(guestsConsumption>500 && guestsConsumption <=1500) {
            slabTwoCount = guestsConsumption - 500;
        } else if(guestsConsumption > 1500)
        {
            slabTwoCount = 1000;
        }

        if(guestsConsumption > 1500 && guestsConsumption <=3000) {
            slabThreeCount = guestsConsumption - 1500;
        }
        else if (guestsConsumption > 3000)
            slabThreeCount = 1500;

        if(guestsConsumption > 3000) {
            slabFourCount = guestsConsumption - 3000;
        }

        int slabOneCost = slabOneCount * 2;
        int slabTwoCost = slabTwoCount * 3;
        int slabThreeCost = slabThreeCount * 5;
        int slabFourCost = slabFourCount * 8;
        cost = slabOneCost + slabTwoCost + slabThreeCost + slabFourCost;
        System.out.println(cost);

        return cost;
    }
}
