package ctictravel.ctictravel.DAO.Admins;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.Admins;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;

import java.util.*;

@Repository
@Transactional
public class AdminsImpl implements AdminsInterfaces {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CommunicationInterface createAdmin(Admins admin) {
        try {
            List<Admins> admins = entityManager.createQuery("SELECT a FROM Admins a WHERE a.adminEmail = :adminEmail OR a.adminPhone = :adminPhone", Admins.class)
                    .setParameter("adminEmail", admin.getAdminEmail())
                    .setParameter("adminPhone", admin.getAdminPhone())
                    .getResultList();
            if (!admins.isEmpty())
                throw new Exception("Admin already exists");

            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1, 1024, 1, admin.getAdminPassword());
            admin.setAdminPassword(hash);
            entityManager.persist(admin);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Admin created successfully").build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }
    @Override
    public CommunicationInterface updateAdmin(Admins admin) {
        try {
            List<Admins> admins = entityManager.createQuery("SELECT a FROM Admins a WHERE a.adminEmail = :adminEmail", Admins.class)
                    .setParameter("adminEmail", admin.getAdminEmail())
                    .getResultList();
            if (admins.isEmpty())
                throw new Exception("Admin not found");
            Admins existingAdmin = admins.get(0);
            existingAdmin.setAdminName(admin.getAdminName());
            existingAdmin.setAdminLastName(admin.getAdminLastName());
            existingAdmin.setAdminPhone(admin.getAdminPhone());
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1, 1024, 1, admin.getAdminPassword());
            existingAdmin.setAdminPassword(hash);
            entityManager.persist(existingAdmin);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Admin updated successfully").build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }
    @Override
    public CommunicationInterface loginAdmin(Admins admin) {
        try {
            List<Admins> admins = entityManager.createQuery("SELECT a FROM Admins a WHERE a.adminEmail = :adminEmail", Admins.class)
                    .setParameter("adminEmail", admin.getAdminEmail())
                    .getResultList();
            if (admins.isEmpty())
                throw new Exception("Admin not found");

            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            if (!argon2.verify(admins.get(0).getAdminPassword(), admin.getAdminPassword()))
                throw new Exception("Incorrect password");

            Map<String, Object> adminData = new HashMap<>(
                    Map.of(
                            "adminId", admins.get(0).getAdminId(),
                            "adminName", admins.get(0).getAdminName(),
                            "adminLastName", admins.get(0).getAdminLastName(),
                            "adminEmail", admins.get(0).getAdminEmail(),
                            "adminPhone", admins.get(0).getAdminPhone()
                    )
            );
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("Admin logged successfully").setData(adminData).build();
        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }
}
