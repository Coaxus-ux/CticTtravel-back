package ctictravel.ctictravel.DAO.Admins;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.Admins;

import java.util.List;

public interface AdminsInterfaces {
    CommunicationInterface createAdmin(Admins admin);
    CommunicationInterface updateAdmin(Admins admin);
    CommunicationInterface loginAdmin( Admins admin);
}
