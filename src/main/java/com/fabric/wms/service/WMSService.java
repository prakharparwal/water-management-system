package com.fabric.wms.service;

import com.fabric.wms.command.Commands;
import com.fabric.wms.exception.InvalidApartmentIdException;
import com.fabric.wms.exception.InvalidNumberOfGuestsException;
import com.fabric.wms.factory.ApartmentFactory;
import com.fabric.wms.model.Apartment;
import com.fabric.wms.model.WaterAllotmentDetails;
import com.fabric.wms.util.BillUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WMSService {

    private final ApartmentFactory apartmentFactory;

    public WMSService(ApartmentFactory apartmentFactory) {
        this.apartmentFactory = apartmentFactory;
    }

    public String allotWater(String allotmentCommand) {
        String allotmentDetails[] = allotmentCommand.split(" ");
        String command = allotmentDetails[0];
        Integer apartmentType = Integer.parseInt(allotmentDetails[1]);
        String distributionRatio = allotmentDetails[2];
        /*
        * This logic can also be enhanced. The conversion can be done by a mapper layer which maps the array
        * of string to an object.
        * We can use either MapStruct/JMapper for this
        * */


        if(isValidAllotmentCommand(command)) {

            /*
            * The below code can be replaced with a Factory design patter where we can pass the apartmentType
            * and it will return the specific apartment.
            * If/else ladder will get removed as well with the factory approach
            * */

            Apartment apartment = apartmentFactory.getApartment(apartmentType);



                WaterAllotmentDetails waterAllotmentDetails= new WaterAllotmentDetails();
                waterAllotmentDetails.setRatio(distributionRatio);
                waterAllotmentDetails.setAllotmentDate(LocalDate.now());
                apartment.setWaterAllotmentDetails(waterAllotmentDetails);
                //Save details to DB
        }
        /*
        * Else
        *   throw an exception InvalidCommandException.
        *   Advise layer than mask the exception into specific error response with code
        *
        * */
        return "Allotment done successfully";
    }

    /*
    * We can use also extract out one validation layer but for now, keeping this under service only
    * Since we don't have much use case. But can be extracted if there is a lot of business checks
    * and validations.
    * */
    public boolean isValidAllotmentCommand(String command) {
        return command.equals(Commands.ALLOT_WATER);
    }

    public String addGuests(Integer apartmentId, Integer numberOfGuests) {


        /*
        * The below code should be extracted out into separate method like something like validation check
        * Also we can create one BusinessException instead of having three different exception InvalidApartmentIdException
        * InvalidNumberOfGuestsException, InvalidApartmentType.
        * Since the use cases and scope is limited I've created separate exception
        * */
        if(apartmentId == -1)
            throw new InvalidApartmentIdException("Invalid apartmentId");
        if(numberOfGuests<0) {
            throw new InvalidNumberOfGuestsException("Invalid number of guests");
        }

        //Fetch apartment from the DB using the apartmentId.

        //Apartment apartment = ApartmentRepository.findById(apartmentId);
        //This will give us Optional of apartment. We can also throw another business exception if Optional is empty
        //Which means there is no apartment for the given Id

        /*
        * Fow now I am using a hardcoded 2BedroomApartment
        * */

        Apartment apartment = apartmentFactory.getApartment(2);
        apartment.setGuests(numberOfGuests);

        //We can save the details to DB
        return "Guests added successfully";
    }

    public String getBill(int apartmentId) {

        //We can use apartmentId to get apartmentDetails from DB

        //The below code is a place holder code, which is required to get total consumption and total cost
        Apartment apartment = apartmentFactory.getApartment(2);
        WaterAllotmentDetails waterAllotmentDetails= new WaterAllotmentDetails();
        waterAllotmentDetails.setRatio("3: 7");
        waterAllotmentDetails.setAllotmentDate(LocalDate.now());
        apartment.setWaterAllotmentDetails(waterAllotmentDetails);

        apartment.setGuests(2);
        apartment.setGuests(3);

        int totalConsumption = getTotalConsumption(apartment);
        int billCost = calculateBillCost(apartment, totalConsumption);
        return totalConsumption+" "+billCost;
    }

    public int getTotalConsumption(Apartment apartment) {
        return apartment.getMonthlyBaseWaterConsumption() + apartment.getGuestsConsumption();
    }

    public int calculateBillCost(Apartment apartment, int totalBaseConsumption) {
        String distributionRatio = apartment.getWaterAllotmentDetails().getRatio();
        int[] distributions = BillUtil.getDistribution(distributionRatio, totalBaseConsumption);
        int totalBaseCost = BillUtil.getCorporationWaterAmount(distributions[0]) +
                BillUtil.getBorewellWaterAmount(distributions[1]);
        int extraConsumptionCost = BillUtil.getTankerConsumptionCost(apartment.getGuestsConsumption());
        return totalBaseCost+extraConsumptionCost;
    }

}
