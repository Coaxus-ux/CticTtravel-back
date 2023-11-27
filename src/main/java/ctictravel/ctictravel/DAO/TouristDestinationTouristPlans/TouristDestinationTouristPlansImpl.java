package ctictravel.ctictravel.DAO.TouristDestinationTouristPlans;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.TouristDestinationTouristPlans;
import ctictravel.ctictravel.Utils.DTOUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class TouristDestinationTouristPlansImpl implements TouristDestinationTouristPlansInterfaces{
     @PersistenceContext
     EntityManager entityManager;
    @Override
    public CommunicationInterface addTouristDestinationTouristPlans(TouristDestinationTouristPlans touristDestinationTouristPlans) {
        try {
            List<TouristDestinationTouristPlans> availableTouristDestinationTouristPlans = entityManager.createQuery("SELECT t FROM TouristDestinationTouristPlans t WHERE t.touristDestination = :touristDestination AND t.touristPlan = :touristPlan", TouristDestinationTouristPlans.class)
                    .setParameter("touristDestination", touristDestinationTouristPlans.getTouristDestination())
                    .setParameter("touristPlan", touristDestinationTouristPlans.getTouristPlan())
                    .getResultList();
            if (!availableTouristDestinationTouristPlans.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("TouristDestinationTouristPlans already exists").build();
            entityManager.persist(touristDestinationTouristPlans);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("TouristDestinationTouristPlans created successfully").build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getTouristDestinationTouristPlansByTouristDestination(TouristDestinationTouristPlans touristDestinationTouristPlans) {
        try{
            List<TouristDestinationTouristPlans> existingTouristDestinationTouristPlans = entityManager.createQuery("SELECT t FROM TouristDestinationTouristPlans t WHERE t.touristDestination = :touristDestination", TouristDestinationTouristPlans.class)
                    .setParameter("touristDestination", touristDestinationTouristPlans.getTouristDestination())
                    .getResultList();
            if (existingTouristDestinationTouristPlans.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("TouristDestinationTouristPlans not found").build();

            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("TouristDestinationTouristPlans found").setData(DTOUtils.convertTouristDestinationTouristPlansToMap(existingTouristDestinationTouristPlans)).build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getTouristDestinationTouristPlansByTouristPlan(TouristDestinationTouristPlans touristDestinationTouristPlans) {
        try{
            List<TouristDestinationTouristPlans> existingTouristDestinationTouristPlans = entityManager.createQuery("SELECT t FROM TouristDestinationTouristPlans t WHERE t.touristPlan = :touristPlan", TouristDestinationTouristPlans.class)
                    .setParameter("touristPlan", touristDestinationTouristPlans.getTouristPlan())
                    .getResultList();
            if (existingTouristDestinationTouristPlans.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("TouristDestinationTouristPlans not found").build();

            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("TouristDestinationTouristPlans found").setData(DTOUtils.convertTouristDestinationTouristPlansToMap(existingTouristDestinationTouristPlans)).build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }
}
