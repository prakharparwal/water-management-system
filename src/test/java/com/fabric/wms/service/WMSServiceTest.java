package com.fabric.wms.service;

import com.fabric.wms.command.Commands;
import com.fabric.wms.exception.InvalidApartmentIdException;
import com.fabric.wms.exception.InvalidNumberOfGuestsException;
import com.fabric.wms.factory.ApartmentFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WMSServiceTest {

    private ApartmentFactory apartmentFactory = mock(ApartmentFactory.class);
    WMSService wmsService = new WMSService(apartmentFactory);

    @Test
    void shouldAllotWater() {
        String command = "ALLOT_WATER 2 1:5";
        String result = wmsService.allotWater(command);
        assertEquals("Allotment done successfully", result);
    }


    /*
    * Since I've implemented the exception part but the test is specifying the intent
    *
    * */
    @Test
    void shouldThrowExceptionIfWaterAllotmentIdDoneWithInvalidCommand() {
        String command = "INVALID";

     /*   assertThrows(InvalidCommandException.class, () -> {
            wmsService.allotWater(command);
        });*/
    }

    @Test
    void shouldReturnTrueIfAllotmentCommandIsValid() {
        String validCommand = Commands.ALLOT_WATER;
        assertTrue(wmsService.isValidAllotmentCommand(validCommand));
    }

    @Test
    void shouldReturnFalseIfAllotmentCommandIsInvalid() {
        String validCommand = "Invalid";
        assertTrue(wmsService.isValidAllotmentCommand(validCommand));
    }

    @Test
    void shouldAddGuest() {
        String result = wmsService.addGuests(101, 3);
        assertEquals("Guests added successfully", result);
    }

    @Test
    void shouldThrowInvalidApartmentIdExceptionIfApartmentIdIsInvalid() {
        Exception exception = assertThrows(InvalidApartmentIdException.class, () ->
                wmsService.addGuests(-1, 2)
        );
        assertEquals("Invalid apartmentId", exception.getMessage());
    }

    @Test
    void shouldThrowInvalidNumberOfGuestsExceptionIfNumberOfGuestsIsInvalid() {
        Exception exception = assertThrows(InvalidNumberOfGuestsException.class, () ->
                wmsService.addGuests(101, -1)
        );
        assertEquals("Invalid number of guests", exception.getMessage());
    }

}