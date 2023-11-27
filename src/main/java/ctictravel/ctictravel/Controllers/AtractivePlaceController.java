package ctictravel.ctictravel.Controllers;


import ctictravel.ctictravel.DAO.AtractivePlaces.AtractivePlacesInterfaces;
import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Interfaces.ResponseEntityInterface;
import ctictravel.ctictravel.Models.AtractivePlaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/attractive-places")
public class AtractivePlaceController {
    @Autowired
    private AtractivePlacesInterfaces atractivePlacesInterfaces;

    @PostMapping(value = "/get-by-country-and-state", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getAtractivePlaces(@RequestBody AtractivePlaces atractivePlace) {
        if (Stream.of(atractivePlace.getAtractivePlaceCountry(), atractivePlace.getAtractivePlaceState()).anyMatch(x -> x == null || x.isEmpty()))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = atractivePlacesInterfaces.getAtractivePlaces(atractivePlace);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Atractive places retrieved successfully").setData(response.getData()).build());
    }

    @PostMapping(value = "/get-by-id", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getAtractivePlaceById(@RequestBody AtractivePlaces atractivePlace) {
        if (Stream.of(atractivePlace.getAtractivePlaceId()).anyMatch(x -> x == null || x.toString().isEmpty()))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = atractivePlacesInterfaces.getAtractivePlaceById(atractivePlace);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Atractive place retrieved successfully").setData(response.getData()).build());
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> createAtractivePlace(@RequestBody AtractivePlaces atractivePlace) {
        if (atractivePlace.hasEmptyNullFields() || Stream.of(atractivePlace.getTouristDestination().getTouristDestinationId()).anyMatch(x -> x == null || x.toString().isEmpty()))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = atractivePlacesInterfaces.createAtractivePlace(atractivePlace);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Atractive place created successfully").build());
    }
    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> updateAtractivePlace(@RequestBody AtractivePlaces atractivePlace) {
        if (atractivePlace.hasEmptyNullFields())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = atractivePlacesInterfaces.updateAtractivePlace(atractivePlace);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Atractive place updated successfully").build());
    }
    @PostMapping(value = "/get-by-tourist-destination", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getAtractivePlacesByTouristDestination(@RequestBody AtractivePlaces atractivePlace) {
        if (Stream.of(atractivePlace.getTouristDestination().getTouristDestinationId()).anyMatch(x -> x == null || x.toString().isEmpty()))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = atractivePlacesInterfaces.getAtractivePlacesByTouristDestination(atractivePlace);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Atractive places retrieved successfully").setData(response.getData()).build());
    }
}
