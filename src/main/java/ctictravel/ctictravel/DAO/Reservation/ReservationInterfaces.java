package ctictravel.ctictravel.DAO.Reservation;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.Reservations;

public interface ReservationInterfaces {
    CommunicationInterface addReservation(Reservations reservation);
    CommunicationInterface cancelReservation(Reservations reservation);

    CommunicationInterface getReservations(Reservations reservation);

    CommunicationInterface getReservationsByTouristPlan(Reservations reservation);



}
