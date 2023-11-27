package ctictravel.ctictravel.DAO.AccommodationTypes;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.AccommodationTypes;

public interface AccommodationTypesInterfaces {
    CommunicationInterface getAccommodationTypes();
    CommunicationInterface getAccommodationTypeById(AccommodationTypes accommodationType);

    CommunicationInterface addAccommodationType(AccommodationTypes accommodationType);
}
