package ctictravel.ctictravel.DAO.AccommodationsTouristPlans;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.AccommodationsTouristPlans;

public interface AccommodationsTouristPlansInterfaces {
    CommunicationInterface addAccommodationsTouristPlans(AccommodationsTouristPlans accommodationsTouristPlans);
    CommunicationInterface getAccommodationsTouristPlansByTouristPlan(AccommodationsTouristPlans accommodationsTouristPlans);
    CommunicationInterface getAccommodationsTouristPlansByAccommodation(AccommodationsTouristPlans accommodationsTouristPlans);

    CommunicationInterface getAccommodationsTouristPlansByTouristPlanAndAccommodation(AccommodationsTouristPlans accommodationsTouristPlans);
}
