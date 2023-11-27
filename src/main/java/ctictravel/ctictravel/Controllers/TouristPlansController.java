package ctictravel.ctictravel.Controllers;

import ctictravel.ctictravel.DAO.TouristPlans.TouristPlansInterfaces;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Interfaces.ResponseEntityInterface;
import ctictravel.ctictravel.Models.TouristPlans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tourist-plans")
public class TouristPlansController {
    @Autowired
    private TouristPlansInterfaces touristPlansInterface;

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> createTouristPlan(@RequestBody TouristPlans touristPlan) {
        System.out.println(touristPlan);
        if(touristPlan.hasEmptyFields())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        try {
            CommunicationInterface response = touristPlansInterface.createTouristPlan(touristPlan);
            if (!response.getSuccessful())
                return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
            return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Tourist Plan created successfully").build());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build());
        }
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> updateTouristPlan(@RequestBody TouristPlans touristPlan) {
        if(touristPlan.hasEmptyFields() || touristPlan.getTouristPlanId() == null || touristPlan.getTouristPlanId().toString().isEmpty())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        try {
            CommunicationInterface response = touristPlansInterface.updateTouristPlan(touristPlan);
            if (!response.getSuccessful())
                return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
            return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Tourist Plan updated successfully").build());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build());
        }
    }

    @PostMapping(value = "/desactivate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> desactivateTouristPlan(@RequestBody TouristPlans touristPlan) {
        if(touristPlan.getTouristPlanId() == null || touristPlan.getTouristPlanId().toString().isEmpty())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        try {
            CommunicationInterface response = touristPlansInterface.desactivateTouristPlan(touristPlan);
            if (!response.getSuccessful())
                return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
            return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Tourist Plan desactivated successfully").build());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build());
        }
    }
    @PostMapping(value = "/activate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> activateTouristPlan(@RequestBody TouristPlans touristPlan) {
        if(touristPlan.getTouristPlanId() == null || touristPlan.getTouristPlanId().toString().isEmpty())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        try {
            CommunicationInterface response = touristPlansInterface.activateTouristPlan(touristPlan);
            if (!response.getSuccessful())
                return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
            return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Tourist Plan activated successfully").build());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build());
        }
    }
    @PostMapping(value = "/get-all", produces = "application/json")
    public ResponseEntity<CommunicationInterface> getAllTouristPlan(@RequestBody TouristPlans touristPlan) {

        try {
            CommunicationInterface response = touristPlansInterface.getTouristPlans(touristPlan.getAdmin());

            if (!response.getSuccessful())
                return ResponseEntity.status(400).body(new CommunicationInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());

            return ResponseEntity.status(200).body(new CommunicationInterface.Builder().setSuccessful(true).setMessage("Some places were found").setData(response.getData()).build());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build());
        }
    }


    @PostMapping(value = "/get-by-id", produces = "application/json")
    public ResponseEntity<CommunicationInterface> getTouristPlanById(@RequestBody TouristPlans touristPlan) {
        if (touristPlan.getTouristPlanId().toString().isEmpty())
            return ResponseEntity.status(400).body(new CommunicationInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        try {
            CommunicationInterface response = touristPlansInterface.getTouristPlanById(touristPlan);
            if (!response.getSuccessful())
                return ResponseEntity.status(400).body(new CommunicationInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
            return ResponseEntity.status(200).body(new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist plan found").setData(response.getData()).build());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build());
        }
    }
}
