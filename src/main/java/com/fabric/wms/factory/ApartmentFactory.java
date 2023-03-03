package com.fabric.wms.factory;

import com.fabric.wms.exception.InvalidApartmentTypeException;
import com.fabric.wms.model.Apartment;
import com.fabric.wms.model.ThreeBedroomApartment;
import com.fabric.wms.model.TwoBedroomApartment;
import org.springframework.stereotype.Component;

@Component
public class ApartmentFactory {

    public Apartment getApartment(int apartmentType) {

        Apartment apartment = switch (apartmentType) {
            case 2: yield new TwoBedroomApartment();
            case 3: yield new ThreeBedroomApartment();
            default:
                throw new InvalidApartmentTypeException("Invalid apartment");
        };
       return apartment;
    }
}
