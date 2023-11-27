package ctictravel.ctictravel.DAO.TransportMethods;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.TransportMethods;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class TransportMethodsImp implements TransportMethodsInterface {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CommunicationInterface getTransportMethods() {
        List<TransportMethods> transportMethods = entityManager.createQuery("SELECT t FROM TransportMethods t", TransportMethods.class).getResultList();
        if (transportMethods.isEmpty()){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Transport Method not found").build();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("transportMethods", transportMethods);

        return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Transport Method found").setData(data).build();
    }

    @Override
    public CommunicationInterface createTransportMethod(String transportMethodType) {
        System.out.println(transportMethodType);
        List <TransportMethods> transportMethods = entityManager.createQuery("SELECT t FROM TransportMethods t WHERE t.transportMethodType = :transportMethodType", TransportMethods.class)
                .setParameter("transportMethodType", transportMethodType)
                .getResultList();
        if (!transportMethods.isEmpty())
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Transport Method already exists").build();
        TransportMethods transportMethod = new TransportMethods();
        transportMethod.setTransportMethodType(transportMethodType);
        entityManager.persist(transportMethod);
        return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Transport Method created successfully").build();
    }

    @Override
    public CommunicationInterface updateTransportMethod( TransportMethods transportMethods) {
        List <TransportMethods> transportMethodsDataBase = entityManager.createQuery("SELECT t FROM TransportMethods t WHERE t.transportMethodId = :transportMethodId", TransportMethods.class)
                .setParameter("transportMethodId", transportMethods.getTransportMethodId())
                .getResultList();
        if (transportMethodsDataBase.isEmpty())
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Transport Method not found").build();
        TransportMethods transportMethod = transportMethodsDataBase.get(0);
        transportMethod.updateTransportMethod(transportMethods);
        entityManager.persist(transportMethod);
        return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Transport Method updated successfully").build();

    }
}
