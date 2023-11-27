package ctictravel.ctictravel.DAO.TouristPlans;

import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Models.TouristPlans;

public interface TouristPlansInterfaces {
    CommunicationInterface createTouristPlan(TouristPlans touristPlan);
    CommunicationInterface updateTouristPlan(TouristPlans touristPlan);

    CommunicationInterface getTouristPlanById(TouristPlans touristPlan);

    CommunicationInterface desactivateTouristPlan(TouristPlans touristPlan);
    CommunicationInterface activateTouristPlan(TouristPlans touristPlan);

    CommunicationInterface getTouristPlans();


}
