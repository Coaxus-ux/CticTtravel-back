package ctictravel.ctictravel.DAO.Accommodations;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.Accommodations;
import ctictravel.ctictravel.Models.TouristPlans;

public interface AccommodationsInterfaces {
    CommunicationInterface getAccommodations();
    CommunicationInterface getAccommodationsByLocation(Accommodations accommodation);
    CommunicationInterface getAccommodationsByType(Accommodations accommodation);

    CommunicationInterface createAccommodation(Accommodations accommodation);

    CommunicationInterface updateAccommodation(Accommodations accommodation);




}
