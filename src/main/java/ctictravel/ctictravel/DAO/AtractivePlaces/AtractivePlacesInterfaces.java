package ctictravel.ctictravel.DAO.AtractivePlaces;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.AtractivePlaces;
public interface AtractivePlacesInterfaces {
    CommunicationInterface getAtractivePlaces(AtractivePlaces atractivePlace);
    CommunicationInterface getAtractivePlaceById(AtractivePlaces atractivePlace);
    CommunicationInterface createAtractivePlace(AtractivePlaces atractivePlace);
    CommunicationInterface updateAtractivePlace(AtractivePlaces atractivePlace);

    CommunicationInterface getAtractivePlacesByTouristDestination(AtractivePlaces atractivePlace);

}
