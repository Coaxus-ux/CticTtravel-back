package ctictravel.ctictravel.DAO.Accommodations;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.Accommodations;
import ctictravel.ctictravel.Utils.DTOUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Transactional
public class AccommodationsImpl implements AccommodationsInterfaces{
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public CommunicationInterface getAccommodations() {
        try {
            List<Accommodations> accommodations = entityManager.createQuery("SELECT a FROM Accommodations a", Accommodations.class)
                    .getResultList();
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Accommodations retrieved successfully").setData(DTOUtils.convertAccommodationToMap(accommodations)).build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getAccommodationsByLocation(Accommodations accommodation) {
        try {
            List<Accommodations> availableAccommodations = entityManager.createQuery("SELECT a FROM Accommodations a WHERE a.accommodationCountry = :country AND a.accommodationCity = :city AND a.accommodationState = :state", Accommodations.class)
                    .setParameter("country", accommodation.getAccommodationCountry())
                    .setParameter("city", accommodation.getAccommodationCity())
                    .setParameter("state", accommodation.getAccommodationState())
                    .getResultList();
            if (availableAccommodations.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Accommodation not found").build();
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Accommodation retrieved successfully").setData(DTOUtils.convertAccommodationToMap(availableAccommodations)).build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getAccommodationsByType(Accommodations accommodation) {
        try {
            List<Accommodations> availableAccommodations = entityManager.createQuery("SELECT a FROM Accommodations a WHERE a.accommodationType = :accommodationType", Accommodations.class)
                    .setParameter("accommodationType", accommodation.getAccommodationType())
                    .getResultList();
            if (availableAccommodations.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Accommodation not found").build();
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Accommodation retrieved successfully").setData(DTOUtils.convertAccommodationToMap(availableAccommodations)).build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }



    @Override
    public CommunicationInterface createAccommodation(Accommodations accommodation) {
        try {
            List<Accommodations> availableAccommodations = entityManager.createQuery("SELECT a FROM Accommodations a WHERE UPPER(a.accommodationName) = UPPER(:accommodationName) AND a.accommodationCountry = :country AND a.accommodationCity = :city AND a.accommodationState = :state", Accommodations.class)
                    .setParameter("accommodationName", accommodation.getAccommodationName())
                    .setParameter("country", accommodation.getAccommodationCountry())
                    .setParameter("city", accommodation.getAccommodationCity())
                    .setParameter("state", accommodation.getAccommodationState())
                    .getResultList();
            if (!availableAccommodations.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Accommodation already exists").build();
            entityManager.persist(accommodation);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Accommodation created successfully").build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface updateAccommodation(Accommodations accommodation) {
        try {
            Accommodations existingAccommodation = entityManager.find(Accommodations.class, accommodation.getAccommodationId());
            if (existingAccommodation == null)
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Accommodation not found").build();
            existingAccommodation.updateAccommodations(accommodation);
            entityManager.merge(existingAccommodation);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Accommodation updated successfully").build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }
}
