package ctictravel.ctictravel.DAO.AccommodationTypes;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.AccommodationTypes;
import ctictravel.ctictravel.Utils.DTOUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class AccommodationTypesImpl implements AccommodationTypesInterfaces{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CommunicationInterface getAccommodationTypes() {
        try {
            List<AccommodationTypes> accommodationTypes = entityManager.createQuery("SELECT at FROM AccommodationTypes at", AccommodationTypes.class)
                    .getResultList();
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Accommodation types retrieved successfully").setData(DTOUtils.convertAccommodationTypesToMap(accommodationTypes)).build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getAccommodationTypeById(AccommodationTypes accommodationType) {
        try {
            List<AccommodationTypes> availableAccommodationTypes = entityManager.createQuery("SELECT at FROM AccommodationTypes at WHERE at.accommodationTypeId = :accommodationTypeId", AccommodationTypes.class)
                    .setParameter("accommodationTypeId", accommodationType.getAccommodationTypeId())
                    .getResultList();
            if (availableAccommodationTypes.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Accommodation type not found").build();
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Accommodation type retrieved successfully").setData(DTOUtils.convertAccommodationTypesToMap(availableAccommodationTypes)).build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface addAccommodationType(AccommodationTypes accommodationType) {
        try{
            List<AccommodationTypes> availableAccommodationTypes = entityManager.createQuery("SELECT at FROM AccommodationTypes at WHERE at.accommodationTypeName = :accommodationTypeName", AccommodationTypes.class)
                    .setParameter("accommodationTypeName", accommodationType.getAccommodationTypeName())
                    .getResultList();
            if (!availableAccommodationTypes.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Accommodation type already exists").build();
            entityManager.persist(accommodationType);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Accommodation type created successfully").build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }
}
