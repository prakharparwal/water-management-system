package com.fabric.wms.factory;

import com.fabric.wms.exception.InvalidApartmentTypeException;
import com.fabric.wms.model.Apartment;
import com.fabric.wms.model.ThreeBedroomApartment;
import com.fabric.wms.model.TwoBedroomApartment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentFactoryTest {

    private ApartmentFactory apartmentFactory = new ApartmentFactory();

    @Test
    void shouldReturnTwoBedroomApartmentWhenProvidedApartmentTypeAs2() {
        Apartment apartment = apartmentFactory.getApartment(2);
        assertTrue(apartment instanceof TwoBedroomApartment);
    }

    @Test
    void shouldReturnTwoBedroomApartmentWhenProvidedApartmentTypeAs3() {
        Apartment apartment = apartmentFactory.getApartment(3);
        assertTrue(apartment instanceof ThreeBedroomApartment);
    }


    @Test
    void shouldReturnInvalidApartmentTypeExcpetionIfApartmentTypeIsInvalid() {
        Exception exception = assertThrows(InvalidApartmentTypeException.class, () -> {
            apartmentFactory.getApartment(1);
        });

        assertEquals("Invalid apartment", exception.getMessage());
    }

}