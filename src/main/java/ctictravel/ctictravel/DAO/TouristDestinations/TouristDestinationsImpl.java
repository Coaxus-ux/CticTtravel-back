package ctictravel.ctictravel.DAO.TouristDestinations;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.TouristDestination;
import ctictravel.ctictravel.Utils.DTOUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class TouristDestinationsImpl implements TouristDestinationsInterfaces {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CommunicationInterface getTouristDestinations(TouristDestination touristDestination) {
        try {
            List<TouristDestination> touristDestinations = entityManager.createQuery("SELECT t FROM TouristDestination t WHERE t.touristDestinationCountry = :country", TouristDestination.class)
                    .setParameter("country", touristDestination.getTouristDestinationCountry())
                    .getResultList();
            if (touristDestinations.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Tourist destination not found").build();


            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist destination found").setData(DTOUtils.convertDestinationsToMap(touristDestinations)).build();

        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getTouristDestinationsById(TouristDestination touristDestination) {
        try {
            TouristDestination existingTouristDestination = entityManager.find(TouristDestination.class, touristDestination.getTouristDestinationId());
            if (existingTouristDestination == null)
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Tourist destination not found").build();

            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist destination found").setData(DTOUtils.convertDestinationsToMap(List.of(existingTouristDestination))).build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface createTouristDestinations(TouristDestination touristDestination) {
        try {
            List<TouristDestination> existingTouristDestination = entityManager.createQuery("SELECT t FROM TouristDestination t WHERE t.touristDestinationCountry = :country AND t.touristDestinationState = :state", TouristDestination.class)
                    .setParameter("country", touristDestination.getTouristDestinationCountry())
                    .setParameter("state", touristDestination.getTouristDestinationState())
                    .getResultList();
            if (!existingTouristDestination.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Tourist destination already exists").build();

            entityManager.persist(touristDestination);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist destination created successfully").build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface updateTouristDestinations(TouristDestination touristDestination) {
        try {
            TouristDestination existingTouristDestination = entityManager.find(TouristDestination.class, touristDestination.getTouristDestinationId());
            if (existingTouristDestination == null)
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Tourist destination not found").build();

            existingTouristDestination.setTouristDestinationCountry(touristDestination.getTouristDestinationCountry());
            existingTouristDestination.setTouristDestinationState(touristDestination.getTouristDestinationState());
            entityManager.persist(existingTouristDestination);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist destination updated successfully").build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getAllTouristDestinations() {
       try {
              List<TouristDestination> touristDestinations = entityManager.createQuery("SELECT t FROM TouristDestination t", TouristDestination.class).getResultList();
              if (touristDestinations.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Tourist destination not found").build();

              return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Tourist destination found").setData(DTOUtils.convertDestinationsToMap(touristDestinations)).build();
       }catch (Exception e){
           return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
       }
    }
}
