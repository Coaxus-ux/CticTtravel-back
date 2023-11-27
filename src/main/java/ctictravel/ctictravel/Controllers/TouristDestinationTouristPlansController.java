package ctictravel.ctictravel.Controllers;

import ctictravel.ctictravel.DAO.TouristDestinationTouristPlans.TouristDestinationTouristPlansInterfaces;
import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Interfaces.ResponseEntityInterface;
import ctictravel.ctictravel.Models.TouristDestinationTouristPlans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tourist-destination-tourist-plans")
public class TouristDestinationTouristPlansController {
    @Autowired
    private TouristDestinationTouristPlansInterfaces touristDestinationTouristPlansInterfaces;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> addTouristDestinationTouristPlans(@RequestBody TouristDestinationTouristPlans touristDestinationTouristPlans) {

        if (touristDestinationTouristPlans.hasEmptyNullFields())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = touristDestinationTouristPlansInterfaces.addTouristDestinationTouristPlans(touristDestinationTouristPlans);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("TouristDestinationTouristPlans created successfully").build());
    }
    @PostMapping(value = "/get-by-tourist-destination", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getTouristDestinationTouristPlansByTouristDestination(@RequestBody TouristDestinationTouristPlans touristDestinationTouristPlans) {
        if (touristDestinationTouristPlans.getTouristDestination() == null)
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = touristDestinationTouristPlansInterfaces.getTouristDestinationTouristPlansByTouristDestination(touristDestinationTouristPlans);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("TouristDestinationTouristPlans retrieved successfully").setData(response.getData()).build());
    }
    @PostMapping(value = "/get-by-tourist-plan", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getTouristDestinationTouristPlansByTouristPlan(@RequestBody TouristDestinationTouristPlans touristDestinationTouristPlans) {
        if (touristDestinationTouristPlans.getTouristPlan() == null)
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = touristDestinationTouristPlansInterfaces.getTouristDestinationTouristPlansByTouristPlan(touristDestinationTouristPlans);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("TouristDestinationTouristPlans retrieved successfully").setData(response.getData()).build());
    }

}
