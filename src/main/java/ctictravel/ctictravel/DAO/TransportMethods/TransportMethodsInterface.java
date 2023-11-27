package ctictravel.ctictravel.DAO.TransportMethods;

import ctictravel.ctictravel.Interfaces.ResponseEntityInterface;
import ctictravel.ctictravel.Models.TransportMethods;

import java.util.UUID;

public interface TransportMethodsInterface {
    ResponseEntityInterface getTransportMethods();

    ResponseEntityInterface createTransportMethod(String transportMethodType);

    ResponseEntityInterface updateTransportMethod(TransportMethods transportMethods);
}
