package ctictravel.ctictravel.Controllers;


import ctictravel.ctictravel.DAO.Reservation.ReservationInterfaces;
import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Interfaces.ResponseEntityInterface;
import ctictravel.ctictravel.Models.Reservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {
    @Autowired
    private ReservationInterfaces reservationInterfaces;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> addReservation(@RequestBody Reservations reservation) {

        if (reservation.hasEmptyNullFields())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = reservationInterfaces.addReservation(reservation);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Reservation created successfully").build());
    }

    @PostMapping(value = "/cancel", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> cancelReservation(@RequestBody Reservations reservation) {
        if (Stream.of(reservation.getReservationId()).anyMatch(x -> x == null || x.toString().isEmpty()))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = reservationInterfaces.cancelReservation(reservation);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Reservation cancelled successfully").build());
    }

    @PostMapping(value = "/get-by-user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getReservations(@RequestBody Reservations reservation) {
        if (Stream.of(reservation.getUser()).anyMatch(x -> x == null || x.toString().isEmpty()))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = reservationInterfaces.getReservations(reservation);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Reservations retrieved successfully").setData(response.getData()).build());
    }
    @PostMapping(value = "/get-by-tourist-plan", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getReservationsByTouristPlan(@RequestBody Reservations reservation) {
        if (Stream.of(reservation.getTouristPlan()).anyMatch(x -> x == null || x.toString().isEmpty()))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());
        CommunicationInterface response = reservationInterfaces.getReservationsByTouristPlan(reservation);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Reservations retrieved successfully").setData(response.getData()).build());
    }
}
