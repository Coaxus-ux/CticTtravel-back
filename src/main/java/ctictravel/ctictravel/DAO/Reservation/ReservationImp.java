package ctictravel.ctictravel.DAO.Reservation;
import ctictravel.ctictravel.Utils.DTOUtils;
import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.Reservations;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ReservationImp implements ReservationInterfases{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CommunicationInterface addReservation(Reservations reservation) {
        try {
            List<Reservations> availableReservations = entityManager.createQuery("SELECT r FROM Reservations r WHERE r.user = :user AND r.touristPlan = :touristPlan", Reservations.class)
                    .setParameter("user", reservation.getUser())
                    .setParameter("touristPlan", reservation.getTouristPlan())
                    .getResultList();
            if (!availableReservations.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Reservation already exists").build();
            reservation.setReservationIsActive(true);
            reservation.setReservationDate(new java.util.Date());
            entityManager.persist(reservation);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Reservation created successfully").build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface cancelReservation(Reservations reservation) {
        try {
            Reservations existingReservation = entityManager.find(Reservations.class, reservation.getReservationId());
            if (existingReservation == null)
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Reservation not found").build();
            existingReservation.setReservationIsActive(false);
            entityManager.persist(existingReservation);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Reservation cancelled successfully").build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getReservations(Reservations reservation) {
        try{
            List<Reservations> existingReservation = entityManager.createQuery("SELECT r FROM Reservations r WHERE r.user = :user", Reservations.class)
                    .setParameter("user", reservation.getUser())
                    .getResultList();
            if (existingReservation.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Reservation not found").build();

            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Reservation found").setData(DTOUtils.convertReservationToMap(existingReservation)).build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getReservationsByTouristPlan(Reservations reservation) {
        try {
            List<Reservations> existingReservation = entityManager.createQuery("SELECT r FROM Reservations r WHERE r.touristPlan = :touristPlan", Reservations.class)
                    .setParameter("touristPlan", reservation.getTouristPlan())
                    .getResultList();
            if (existingReservation.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Reservation not found").build();

            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Reservation found").setData(DTOUtils.convertReservationToMap(existingReservation)).build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }

    }
}


