package ctictravel.ctictravel.DAO.TransportMethods;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;

import ctictravel.ctictravel.Models.TransportMethods;

public interface TransportMethodsInterface {
    CommunicationInterface getTransportMethods();

    CommunicationInterface createTransportMethod(String transportMethodType);

    CommunicationInterface updateTransportMethod(TransportMethods transportMethods);
}
