package ctictravel.ctictravel.Controllers;

import ctictravel.ctictravel.DAO.TouristDestinations.TouristDestinationsInterfaces;
import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Interfaces.ResponseEntityInterface;
import ctictravel.ctictravel.Models.TouristDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/touristDestinations")
public class TouristDestinationController {
    @Autowired
    private TouristDestinationsInterfaces touristDestinationsInterfaces;

    @PostMapping(value = "/getTouristDestinations", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getTouristDestinations(@RequestBody TouristDestination touristDestination) {
        if (Stream.of(touristDestination.getTouristDestinationCountry()).anyMatch(value -> value == null || value.isEmpty()))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = touristDestinationsInterfaces.getTouristDestinations(touristDestination);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Tourist destination found").setData(response.getData()).build());
    }
    @PostMapping(value = "/getTouristDestinationsById", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getTouristDestinationsById(@RequestBody TouristDestination touristDestination) {
        if (Stream.of(touristDestination.getTouristDestinationId()).anyMatch(value -> value == null))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = touristDestinationsInterfaces.getTouristDestinationsById(touristDestination);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Tourist destination found").setData(response.getData()).build());
    }
    @PostMapping(value = "/createTouristDestinations", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> createTouristDestinations(@RequestBody TouristDestination touristDestination) {
        if (Stream.of(touristDestination.getTouristDestinationCountry(), touristDestination.getTouristDestinationState()).anyMatch(value -> value == null || value.isEmpty()))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = touristDestinationsInterfaces.createTouristDestinations(touristDestination);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Tourist destination created successfully").build());
    }
    @PostMapping(value = "/updateTouristDestinations", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> updateTouristDestinations(@RequestBody TouristDestination touristDestination) {
        if (Stream.of(touristDestination.getTouristDestinationId().toString()).anyMatch(value -> value == null || value.isEmpty()))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = touristDestinationsInterfaces.updateTouristDestinations(touristDestination);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Tourist destination updated successfully").build());
    }
    @GetMapping(value = "/getAllTouristDestinations", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getAllTouristDestinations() {
        CommunicationInterface response = touristDestinationsInterfaces.getAllTouristDestinations();
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Tourist destination found").setData(response.getData()).build());
    }

}
