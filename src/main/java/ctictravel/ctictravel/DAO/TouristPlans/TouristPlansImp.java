package ctictravel.ctictravel.DAO.TouristPlans;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.TouristPlans;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class TouristPlansImp implements TouristPlansInterfaces {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CommunicationInterface createTouristPlan(TouristPlans touristPlan) {
        try {
            List<TouristPlans> availableTouristPlans = entityManager.createQuery("SELECT tp FROM TouristPlans tp WHERE tp.touristPlanName = :touristPlanName", TouristPlans.class)
                    .setParameter("touristPlanName", touristPlan.getTouristPlanName())
                    .getResultList();
            if (!availableTouristPlans.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Tourist plan already exists").build();
            entityManager.persist(touristPlan);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist plan created successfully").build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface updateTouristPlan(TouristPlans touristPlan) {
        try {
            List<TouristPlans> availableTouristPlans = entityManager.createQuery("SELECT tp FROM TouristPlans tp WHERE tp.touristPlanId = :touristPlanId", TouristPlans.class)
                    .setParameter("touristPlanId", touristPlan.getTouristPlanId())
                    .getResultList();
            if (availableTouristPlans.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Tourist plan not found").build();
            TouristPlans existingTouristPlan = availableTouristPlans.get(0);
            existingTouristPlan.updateTouristPlan(touristPlan);
            entityManager.persist(existingTouristPlan);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist plan updated successfully").build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getTouristPlanById(TouristPlans touristPlan) {
        try {
            List<TouristPlans> availableTouristPlans = entityManager.createQuery("SELECT tp FROM TouristPlans tp WHERE tp.touristPlanId = :touristPlanId", TouristPlans.class)
                    .setParameter("touristPlanId", touristPlan.getTouristPlanId())
                    .getResultList();
            if (availableTouristPlans.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Tourist plan not found").build();
            TouristPlans existingTouristPlan = availableTouristPlans.get(0);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist plan found").setData((Map<String, Object>) existingTouristPlan).build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface desactivateTouristPlan(TouristPlans touristPlan) {
        try {
            List<TouristPlans> availableTouristPlans = entityManager.createQuery("SELECT tp FROM TouristPlans tp WHERE tp.touristPlanId = :touristPlanId", TouristPlans.class)
                    .setParameter("touristPlanId", touristPlan.getTouristPlanId())
                    .getResultList();
            if (availableTouristPlans.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Tourist plan not found").build();
            TouristPlans existingTouristPlan = availableTouristPlans.get(0);
            existingTouristPlan.desactivateTouristPlan();
            entityManager.persist(existingTouristPlan);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist plan desactivated successfully").build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface activateTouristPlan(TouristPlans touristPlan) {
        try {
            List<TouristPlans> availableTouristPlans = entityManager.createQuery("SELECT tp FROM TouristPlans tp WHERE tp.touristPlanId = :touristPlanId", TouristPlans.class)
                    .setParameter("touristPlanId", touristPlan.getTouristPlanId())
                    .getResultList();
            if (availableTouristPlans.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Tourist plan not found").build();
            TouristPlans existingTouristPlan = availableTouristPlans.get(0);
            existingTouristPlan.activateTouristPlan();
            entityManager.persist(existingTouristPlan);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist plan activated successfully").build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getTouristPlans() {
        try {
            List<TouristPlans> availableTouristPlans = entityManager.createQuery("SELECT tp FROM TouristPlans tp", TouristPlans.class)
                    .getResultList();
            if (availableTouristPlans.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Tourist plan not found").build();
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist plan found").setData((Map<String, Object>) availableTouristPlans).build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }
}