package ctictravel.ctictravel.DAO.Users;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.Users;

public interface UsersInterfaces {
    CommunicationInterface login(Users user);
    CommunicationInterface register(Users user);
    CommunicationInterface getUserById(Users user);
    CommunicationInterface updateUser(Users user);

}
