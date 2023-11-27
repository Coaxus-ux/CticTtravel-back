package ctictravel.ctictravel.DAO.TouristPlans;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.Admins;
import ctictravel.ctictravel.Models.TouristPlans;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
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
            List<TouristPlans> availableTouristPlans = entityManager.createQuery("SELECT tp FROM TouristPlans tp WHERE tp.touristPlanName = :touristPlanName AND tp.admin = :admin", TouristPlans.class)
                    .setParameter("touristPlanName", touristPlan.getTouristPlanName())
                    .setParameter("admin", touristPlan.getAdmin())
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
            Map<String, Object> touristPlanData = new HashMap<>();
            touristPlanData.put("touristPlanId", existingTouristPlan.getTouristPlanId());
            touristPlanData.put("touristPlanName", existingTouristPlan.getTouristPlanName());
            touristPlanData.put("touristPlanDescription", existingTouristPlan.getTouristPlanDescription());
            touristPlanData.put("touristPlanPrice", existingTouristPlan.getTouristPlanPrice());
            touristPlanData.put("touristPlanStart", existingTouristPlan.getTouristPlanStart());
            touristPlanData.put("touristPlanEnd", existingTouristPlan.getTouristPlanEnd());
            touristPlanData.put("isAvailable", existingTouristPlan.getIsAvailable());
            touristPlanData.put("transportMethod", existingTouristPlan.getTransportMethod().getTransportMethodType());

            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist plan found").setData(touristPlanData).build();
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
    public CommunicationInterface getTouristPlans(Admins admin) {
        try {
            List<TouristPlans> availableTouristPlans = entityManager.createQuery("SELECT tp FROM TouristPlans tp WHERE tp.admin = :admin AND isAvailable = true", TouristPlans.class)
                    .setParameter("admin", admin)
                    .getResultList();
            if (availableTouristPlans.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Tourist plan not found").build();

            Map<String, Object> plansData = new HashMap<>();
            for (TouristPlans plan : availableTouristPlans) {
                Map<String, Object> planData = new HashMap<>();
                planData.put("touristPlanId", plan.getTouristPlanId());
                planData.put("touristPlanName", plan.getTouristPlanName());
                planData.put("touristPlanDescription", plan.getTouristPlanDescription());
                planData.put("touristPlanPrice", plan.getTouristPlanPrice());
                planData.put("touristPlanStart", plan.getTouristPlanStart());
                planData.put("touristPlanEnd", plan.getTouristPlanEnd());
                planData.put("isAvailable", plan.getIsAvailable());
                planData.put("transportMethod", plan.getTransportMethod().getTransportMethodType());
                plansData.put(plan.getTouristPlanId().toString(), planData);
            }




            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist plan found").setData(plansData).build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }
}
