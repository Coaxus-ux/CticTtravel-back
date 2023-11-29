package ctictravel.ctictravel.DAO.TouristDestinations;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.TouristDestination;

public interface TouristDestinationsInterfaces {
    CommunicationInterface getTouristDestinations(TouristDestination touristDestination);
    CommunicationInterface getTouristDestinationsById(TouristDestination touristDestination);
    CommunicationInterface createTouristDestinations(TouristDestination touristDestination);
    CommunicationInterface updateTouristDestinations(TouristDestination touristDestination);

    CommunicationInterface getAllTouristDestinations();

}
