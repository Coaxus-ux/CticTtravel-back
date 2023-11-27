package ctictravel.ctictravel.DAO.AtractivePlaces;


import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.AtractivePlaces;
import ctictravel.ctictravel.Utils.DTOUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class AtractivePlacesImp implements AtractivePlacesInterfaces {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CommunicationInterface getAtractivePlaces(AtractivePlaces atractivePlace) {
        try {
            List<AtractivePlaces> atractivePlaces = entityManager.createQuery("SELECT a FROM AtractivePlaces a WHERE a.atractivePlaceCountry = :country AND a.atractivePlaceState = :state", AtractivePlaces.class)
                    .setParameter("state", atractivePlace.getAtractivePlaceState())
                    .setParameter("country", atractivePlace.getAtractivePlaceCountry())
                    .getResultList();
            if (atractivePlaces.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Atractive place not found").build();
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Atractive place found").setData(DTOUtils.convertPlacesToMap(atractivePlaces)).build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getAtractivePlaceById(AtractivePlaces atractivePlace) {
        try {
            AtractivePlaces existingAtractivePlace = entityManager.find(AtractivePlaces.class, atractivePlace.getAtractivePlaceId());
            if (existingAtractivePlace == null)
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Atractive place not found").build();
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Atractive place found").setData(DTOUtils.convertPlacesToMap(List.of(existingAtractivePlace))).build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface createAtractivePlace(AtractivePlaces atractivePlace) {
        try {
            List<AtractivePlaces> existingAtractivePlace = entityManager.createQuery("SELECT a FROM AtractivePlaces a WHERE a.atractivePlaceCountry = :country AND a.atractivePlaceState = :state AND a.atractivePlaceCity = :city AND a.atractivePlaceName = :name", AtractivePlaces.class)
                    .setParameter("country", atractivePlace.getAtractivePlaceCountry())
                    .setParameter("state", atractivePlace.getAtractivePlaceState())
                    .setParameter("city", atractivePlace.getAtractivePlaceCity())
                    .setParameter("name", atractivePlace.getAtractivePlaceName())
                    .getResultList();
            if (!existingAtractivePlace.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Atractive place already exists").build();
            entityManager.persist(atractivePlace);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Atractive place created successfully").build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface updateAtractivePlace(AtractivePlaces atractivePlace) {
        try {
            AtractivePlaces existingAtractivePlace = entityManager.find(AtractivePlaces.class, atractivePlace.getAtractivePlaceId());
            if (existingAtractivePlace == null)
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Atractive place not found").build();
            existingAtractivePlace.updateAtractivePlace(atractivePlace);
            entityManager.persist(existingAtractivePlace);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Atractive place updated successfully").build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();

        }
    }

    @Override
    public CommunicationInterface getAtractivePlacesByTouristDestination(AtractivePlaces atractivePlace) {
        try {
            List<AtractivePlaces> atractivePlaces = entityManager.createQuery("SELECT a FROM AtractivePlaces a WHERE a.touristDestination = :touristDestination", AtractivePlaces.class)
                    .setParameter("touristDestination", atractivePlace.getTouristDestination())
                    .getResultList();
            if (atractivePlaces.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Atractive place not found").build();
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Atractive place found").setData(DTOUtils.convertPlacesToMap(atractivePlaces)).build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }

    }
}
