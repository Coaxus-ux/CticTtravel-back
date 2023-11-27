package ctictravel.ctictravel.Controllers;
import ctictravel.ctictravel.DAO.Accommodations.AccommodationsInterfaces;
import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Interfaces.ResponseEntityInterface;

import ctictravel.ctictravel.Models.Accommodations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/accommodation")
public class AccommodationsController {
    @Autowired
    private AccommodationsInterfaces accommodationsInterfaces;


    @GetMapping(value = "/getAccommodations", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getAccommodations() {
        CommunicationInterface response = accommodationsInterfaces.getAccommodations();
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Accommodations retrieved successfully").setData(response.getData()).build());
    }

    @PostMapping(value = "/getAccommodationsByLocation", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getAccommodationsByLocation(@RequestBody Accommodations accommodation) {
        if (accommodation.getAccommodationCountry() == null || accommodation.getAccommodationCountry().isEmpty() || accommodation.getAccommodationCity() == null || accommodation.getAccommodationCity().isEmpty() || accommodation.getAccommodationState() == null || accommodation.getAccommodationState().isEmpty())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        CommunicationInterface response = accommodationsInterfaces.getAccommodationsByLocation(accommodation);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Accommodation retrieved successfully").setData(response.getData()).build());
    }

    @PostMapping(value = "/getAccommodationsByType", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getAccommodationsByType(@RequestBody Accommodations accommodation) {
        if (accommodation.getAccommodationType() == null)
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        CommunicationInterface response = accommodationsInterfaces.getAccommodationsByType(accommodation);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Accommodation retrieved successfully").setData(response.getData()).build());
    }

    @PostMapping(value = "/createAccommodation", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> createAccommodation(@RequestBody Accommodations accommodation) {
        if (accommodation.hasEmptyNullFields())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        CommunicationInterface response = accommodationsInterfaces.createAccommodation(accommodation);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Accommodation created successfully").build());
    }

    @PutMapping(value = "/updateAccommodation", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> updateAccommodation(@RequestBody Accommodations accommodation) {
        if (accommodation.getAccommodationId() == null || accommodation.hasEmptyNullFields())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        CommunicationInterface response = accommodationsInterfaces.updateAccommodation(accommodation);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Accommodation updated successfully").build());
    }
}
