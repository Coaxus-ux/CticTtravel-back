package ctictravel.ctictravel.DAO.AccommodationsTouristPlans;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.AccommodationsTouristPlans;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

import ctictravel.ctictravel.Utils.DTOUtils;

@Repository
@Transactional
public class AccommodationsTouristPlansImpl implements AccommodationsTouristPlansInterfaces {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CommunicationInterface addAccommodationsTouristPlans(AccommodationsTouristPlans accommodationsTouristPlans) {
        try {
            List<AccommodationsTouristPlans> accommodationsTouristPlansList = entityManager.createQuery("SELECT a FROM AccommodationsTouristPlans a WHERE a.accommodation = :accommodation AND a.touristPlan = :touristPlan", AccommodationsTouristPlans.class)
                    .setParameter("accommodation", accommodationsTouristPlans.getAccommodation())
                    .setParameter("touristPlan", accommodationsTouristPlans.getTouristPlan())
                    .getResultList();
            if (!accommodationsTouristPlansList.isEmpty()) {
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Accommodation already added to this tourist plan").build();
            }
            entityManager.persist(accommodationsTouristPlans);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Accommodation added successfully").setData(DTOUtils.convertAccommodationsTouristPlansToMap(accommodationsTouristPlansList)).build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }


    @Override
    public CommunicationInterface getAccommodationsTouristPlansByTouristPlan(AccommodationsTouristPlans accommodationsTouristPlans) {
        try {
            List<AccommodationsTouristPlans> accommodationsTouristPlansList = entityManager.createQuery("SELECT a FROM AccommodationsTouristPlans a WHERE a.touristPlan = :touristPlan", AccommodationsTouristPlans.class)
                    .setParameter("touristPlan", accommodationsTouristPlans.getTouristPlan())
                    .getResultList();
            if (accommodationsTouristPlansList.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Accommodation not found").build();
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Accommodation retrieved successfully").setData(DTOUtils.convertAccommodationsTouristPlansToMap(accommodationsTouristPlansList)).build();

        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getAccommodationsTouristPlansByAccommodation(AccommodationsTouristPlans accommodationsTouristPlans) {
        try {
            List<AccommodationsTouristPlans> accommodationsTouristPlansList = entityManager.createQuery("SELECT a FROM AccommodationsTouristPlans a WHERE a.accommodation = :accommodation", AccommodationsTouristPlans.class)
                    .setParameter("accommodation", accommodationsTouristPlans.getAccommodation())
                    .getResultList();
            if (accommodationsTouristPlansList.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Accommodation not found").build();
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Accommodation retrieved successfully").setData(DTOUtils.convertAccommodationsTouristPlansToMap(accommodationsTouristPlansList)).build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getAccommodationsTouristPlansByTouristPlanAndAccommodation(AccommodationsTouristPlans accommodationsTouristPlans) {
        try {
            List<AccommodationsTouristPlans> accommodationsTouristPlansList = entityManager.createQuery("SELECT a FROM AccommodationsTouristPlans a WHERE a.accommodation = :accommodation AND a.touristPlan = :touristPlan", AccommodationsTouristPlans.class)
                    .setParameter("accommodation", accommodationsTouristPlans.getAccommodation())
                    .setParameter("touristPlan", accommodationsTouristPlans.getTouristPlan())
                    .getResultList();
            if (accommodationsTouristPlansList.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Accommodation not found").build();
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Accommodation retrieved successfully").setData(DTOUtils.convertAccommodationsTouristPlansToMap(accommodationsTouristPlansList)).build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }
}
