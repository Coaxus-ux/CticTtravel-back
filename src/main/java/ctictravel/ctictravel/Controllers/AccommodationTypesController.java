package ctictravel.ctictravel.Controllers;

import ctictravel.ctictravel.DAO.AccommodationTypes.AccommodationTypesInterfaces;
import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Interfaces.ResponseEntityInterface;

import ctictravel.ctictravel.Models.AccommodationTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/accommodationTypes")
public class AccommodationTypesController {
    @Autowired
    private AccommodationTypesInterfaces accommodationTypesInterfaces;

    @GetMapping(value = "/getAccommodationTypes", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getAccommodationTypes() {
        CommunicationInterface response = accommodationTypesInterfaces.getAccommodationTypes();
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Accommodation types retrieved successfully").setData(response.getData()).build());
    }

    @PostMapping(value = "/getAccommodationTypeById", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getAccommodationTypeById(@RequestBody AccommodationTypes accommodationType) {
        if (accommodationType.getAccommodationTypeId() == null)
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        CommunicationInterface response = accommodationTypesInterfaces.getAccommodationTypeById(accommodationType);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Accommodation type retrieved successfully").setData(response.getData()).build());
    }

    @PostMapping(value = "/addAccommodationType", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> addAccommodationType(@RequestBody AccommodationTypes accommodationType) {
        if (accommodationType.getAccommodationTypeName() == null || accommodationType.getAccommodationTypeName().isEmpty())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        CommunicationInterface response = accommodationTypesInterfaces.addAccommodationType(accommodationType);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Accommodation type created successfully").build());
    }

}
