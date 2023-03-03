package com.fabric.wms.model;

public abstract class Apartment implements WaterSystem {

    protected int apartmentId;
    protected int members;
    protected int guests;
    protected int apartmentType;

    protected WaterAllotmentDetails waterAllotmentDetails;

    public int getMembers() {
        return members;
    }

    public int getGuests() {
        return guests;
    }

    public int getApartmentType() {
        return apartmentType;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setGuests(int guests) {
        this.guests = this.getGuests() + guests;
    }

    public WaterAllotmentDetails getWaterAllotmentDetails() {
        return waterAllotmentDetails;
    }

    public void setWaterAllotmentDetails(WaterAllotmentDetails waterAllotmentDetails) {
        this.waterAllotmentDetails = waterAllotmentDetails;
    }

    public int getGuestsConsumption() {
        return guests*10*30;
    }

    public abstract int getMonthlyBaseWaterConsumption();
}
