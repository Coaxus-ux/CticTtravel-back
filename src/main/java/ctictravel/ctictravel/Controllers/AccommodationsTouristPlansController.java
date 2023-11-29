package ctictravel.ctictravel.Controllers;

import ctictravel.ctictravel.DAO.AccommodationsTouristPlans.AccommodationsTouristPlansInterfaces;
import ctictravel.ctictravel.Models.AccommodationsTouristPlans;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Interfaces.ResponseEntityInterface;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
@RequestMapping("/accommodationsTouristPlans")
public class AccommodationsTouristPlansController {
    @Autowired
    private AccommodationsTouristPlansInterfaces accommodationsTouristPlansInterfaces;

    @PostMapping(value = "/addAccommodationsTouristPlans", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> addAccommodationsTouristPlans(@RequestBody AccommodationsTouristPlans accommodationsTouristPlans) {
        if (accommodationsTouristPlans.getAccommodation() == null || accommodationsTouristPlans.getTouristPlan() == null)
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        CommunicationInterface response = accommodationsTouristPlansInterfaces.addAccommodationsTouristPlans(accommodationsTouristPlans);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Accommodation added successfully").build());
    }
    @PostMapping(value = "/getAccommodationsTouristPlansByTouristPlan", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getAccommodationsTouristPlansByTouristPlan(@RequestBody AccommodationsTouristPlans accommodationsTouristPlans) {
        if (accommodationsTouristPlans.getTouristPlan() == null)
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        CommunicationInterface response = accommodationsTouristPlansInterfaces.getAccommodationsTouristPlansByTouristPlan(accommodationsTouristPlans);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Accommodation retrieved successfully").setData(response.getData()).build());
    }
    @PostMapping(value = "/getAccommodationsTouristPlansByAccommodation", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getAccommodationsTouristPlansByAccommodation(@RequestBody AccommodationsTouristPlans accommodationsTouristPlans) {
        if (accommodationsTouristPlans.getAccommodation() == null)
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        CommunicationInterface response = accommodationsTouristPlansInterfaces.getAccommodationsTouristPlansByAccommodation(accommodationsTouristPlans);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Accommodation retrieved successfully").setData(response.getData()).build());
    }
    @PostMapping(value = "/getAccommodationsTouristPlansByTouristPlanAndAccommodation", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getAccommodationsTouristPlansByTouristPlanAndAccommodation(@RequestBody AccommodationsTouristPlans accommodationsTouristPlans) {
        if (accommodationsTouristPlans.getAccommodation() == null || accommodationsTouristPlans.getTouristPlan() == null)
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        CommunicationInterface response = accommodationsTouristPlansInterfaces.getAccommodationsTouristPlansByTouristPlanAndAccommodation(accommodationsTouristPlans);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Accommodation retrieved successfully").setData(response.getData()).build());
    }
}
