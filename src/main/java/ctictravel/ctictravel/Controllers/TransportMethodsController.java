package ctictravel.ctictravel.Controllers;

import ctictravel.ctictravel.DAO.TransportMethods.TransportMethodsInterface;
import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Interfaces.ResponseEntityInterface;
import ctictravel.ctictravel.Models.TransportMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/transport-methods")
public class TransportMethodsController {
    @Autowired
    private TransportMethodsInterface transportMethodsInterface;

    @GetMapping(value = "/types", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getTransportMethods() {
        try {
            CommunicationInterface response = transportMethodsInterface.getTransportMethods();
            if (!response.getSuccessful())
                return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
            return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Transport Methods found").setData(response.getData()).build());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build());
        }
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> createTransportMethod(@RequestBody TransportMethods transportMethods) {
        if(Stream.of(transportMethods.getTransportMethodType()).anyMatch(value -> value == null || value.isEmpty()))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        try {

            CommunicationInterface response = transportMethodsInterface.createTransportMethod(transportMethods.getTransportMethodType());
            if (!response.getSuccessful())
                return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
            return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Transport Method created successfully").build());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build());
        }
    }
    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> updateTransportMethod( @RequestBody TransportMethods transportMethods) {
        if(Stream.of(transportMethods.getTransportMethodType()).anyMatch(value -> value == null || value.isEmpty()))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        try {
            CommunicationInterface response = transportMethodsInterface.updateTransportMethod(transportMethods);
            if (!response.getSuccessful())
                return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
            return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Transport Method updated successfully").build());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build());
        }
    }


}
