package com.fabric.wms.model;

public class ThreeBedroomApartment extends Apartment{

    public ThreeBedroomApartment() {
        this.members = 5;
        this.apartmentType = 3;
    }

    public ThreeBedroomApartment(int guests) {
        this.members = 5;
        this.apartmentType = 3;
        this.guests = guests;
    }

    @Override
    public int getMonthlyBaseWaterConsumption() {
        return members*10*30;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

}
