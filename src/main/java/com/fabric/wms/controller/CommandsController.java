package com.fabric.wms.controller;

import com.fabric.wms.model.BillRespose;
import com.fabric.wms.model.WMSResponse;
import com.fabric.wms.service.WMSService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CommandsController {

    private final WMSService wMSService;

    public CommandsController(WMSService wMSService) {
        this.wMSService = wMSService;
    }

    @PostMapping("/wms/allot-water")
    public ResponseEntity<WMSResponse> allotWater(@RequestBody Map<String, String> command) {

        String allotmentCommand = command.get("allotmentCommand");
        String response = wMSService.allotWater(allotmentCommand);
        return new ResponseEntity<WMSResponse>(new WMSResponse("Success", response), HttpStatus.CREATED);
    }

    @PostMapping("/wms/add-guest")
    public ResponseEntity<WMSResponse> addGuest(@RequestBody Map<String, Integer> guestDetails) {
        String response = wMSService.addGuests(guestDetails.getOrDefault("apartmentId", -1)
                , guestDetails.getOrDefault("numberOfGuests", -1));
        return new ResponseEntity<WMSResponse>(new WMSResponse("Success", response), HttpStatus.OK);
    }

    @GetMapping("/wms/bill")
    public ResponseEntity<String> getBill(@RequestParam int apartmentId) {
        /*
        * Here we can have extend the api to support for month wise bill.
        * For the same we need to have some support storing month wise allotment
        * */
        String response = wMSService.getBill(apartmentId);
        return new ResponseEntity<>("",
                HttpStatus.OK);
    }
}
