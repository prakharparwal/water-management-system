package com.fabric.wms.model;

public class TwoBedroomApartment extends Apartment {

    public TwoBedroomApartment() {
        this.members = 3;
        this.apartmentType = 2;
    }

    public TwoBedroomApartment(int guests) {
        this.members = 3;
        this.apartmentType = 2;
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
