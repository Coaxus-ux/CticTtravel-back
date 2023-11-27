package ctictravel.ctictravel.DAO.Users;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.Users;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class UsersImpl implements UsersInterfaces {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CommunicationInterface login(Users user) {
        try {
            List<Users> users = entityManager.createQuery("SELECT a FROM Users a WHERE a.userEmail = :userEmail", Users.class)
                    .setParameter("userEmail", user.getUserEmail())
                    .getResultList();
            if (users.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("User not found").build();
            Users existingUser = users.get(0);
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            if (!argon2.verify(existingUser.getUserPassword(), user.getUserPassword()))
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("Incorrect password").build();

            Map<String, Object> data = new HashMap<>(
                    Map.of(
                            "userId", existingUser.getUserId(),
                            "userName", existingUser.getUserName(),
                            "userLastName", existingUser.getUserLastName(),
                            "userEmail", existingUser.getUserEmail(),
                            "userPhone", existingUser.getUserPhone(),
                            "userAddress", existingUser.getUserAddress(),
                            "userCity", existingUser.getUserCity(),
                            "userCountry", existingUser.getUserCountry(),
                            "userState", existingUser.getUserState()

                    )
            );
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("User logged successfully").setData(data).build();

        } catch (Exception e) {
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface register(Users user) {
        try{
            List <Users> users = entityManager.createQuery("SELECT u FROM Users u WHERE u.userEmail = :userEmail OR u.userPhone = :userPhone", Users.class)
                    .setParameter("userEmail", user.getUserEmail())
                    .setParameter("userPhone", user.getUserPhone())
                    .getResultList();
            if (!users.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("User already exists").build();
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1, 1024, 1, user.getUserPassword());
            user.setUserPassword(hash);
            entityManager.persist(user);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("User created successfully").build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface getUserById(Users user) {
        try{
            List <Users> users = entityManager.createQuery("SELECT u FROM Users u WHERE u.userId = :userId", Users.class)
                    .setParameter("userId", user.getUserId())
                    .getResultList();
            if (users.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("User not found").build();
            Map<String, Object> data = new HashMap<>(
                    Map.of(
                            "userId", user.getUserId(),
                            "userName", user.getUserName(),
                            "userLastName", user.getUserLastName(),
                            "userEmail", user.getUserEmail(),
                            "userPhone", user.getUserPhone(),
                            "userAddress", user.getUserAddress(),
                            "userCity", user.getUserCity(),
                            "userCountry", user.getUserCountry(),
                            "userState", user.getUserState()

                    )
            );
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("User found").setData(data).build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }

    @Override
    public CommunicationInterface updateUser(Users user) {
        try{
         List <Users> users = entityManager.createQuery("SELECT u FROM Users u WHERE u.userEmail = :userEmail", Users.class)
                 .setParameter("userEmail", user.getUserEmail())
                 .getResultList();
            if (users.isEmpty())
                return new CommunicationInterface.Builder().setSuccessful(false).setMessage("User not found").build();
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1, 1024, 1, user.getUserPassword());
            user.setUserPassword(hash);
            Users existingUser = users.get(0);
            existingUser.updateUser(user);
            entityManager.persist(existingUser);
            return new CommunicationInterface.Builder().setSuccessful(true).setMessage("User updated successfully").build();
        }catch (Exception e){
            return new CommunicationInterface.Builder().setSuccessful(false).setMessage(e.getMessage()).build();
        }
    }
}
