package ctictravel.ctictravel.Utils;

import ctictravel.ctictravel.Models.Reservations;
import ctictravel.ctictravel.Models.TouristDestination;
import ctictravel.ctictravel.Models.TouristPlans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DTOUtils {
    public static Map<String, Object> convertReservationToMap(List<Reservations> existingReservations) {
        Map<String, Object> reservationsUser = new HashMap<>();

        for (Reservations r : existingReservations) {
            Map<String, Object> reservationUser = new HashMap<>();
            reservationUser.put("reservationId", r.getReservationId());
            reservationUser.put("reservationDate", r.getReservationDate());
            reservationUser.put("reservationIsActive", r.getReservationIsActive());
            reservationUser.put("touristPlanId", r.getTouristPlan().getTouristPlanId());
            reservationUser.put("touristPlanName", r.getTouristPlan().getTouristPlanName());
            reservationUser.put("touristPlanDescription", r.getTouristPlan().getTouristPlanDescription());
            reservationUser.put("touristPlanPrice", r.getTouristPlan().getTouristPlanPrice());
            reservationUser.put("touristPlanStart", r.getTouristPlan().getTouristPlanStart());
            reservationUser.put("touristPlanEnd", r.getTouristPlan().getTouristPlanEnd());
            reservationUser.put("transportMethodType", r.getTouristPlan().getTransportMethod().getTransportMethodType());
            reservationUser.put("touristPlanCountry", r.getTouristPlan().getTouristPlanCountry());
            reservationUser.put("adminId", r.getTouristPlan().getAdmin().getAdminId());

            reservationsUser.put(r.getReservationId().toString(), reservationUser);
        }
        return reservationsUser;
    }
    public static Map<String, Object> convertTouristToMap(List<TouristPlans> touristPlans){
        Map<String, Object> touristPlanUser = new HashMap<>();

        for (TouristPlans t : touristPlans) {
            Map<String, Object> touristPlan = new HashMap<>();
            touristPlan.put("touristPlanId", t.getTouristPlanId());
            touristPlan.put("touristPlanName", t.getTouristPlanName());
            touristPlan.put("touristPlanDescription", t.getTouristPlanDescription());
            touristPlan.put("touristPlanPrice", t.getTouristPlanPrice());
            touristPlan.put("touristPlanStart", t.getTouristPlanStart());
            touristPlan.put("touristPlanEnd", t.getTouristPlanEnd());
            touristPlan.put("isAvailable", t.getIsAvailable());
            touristPlan.put("transportMethod", t.getTransportMethod().getTransportMethodType());
            touristPlan.put("touristPlanCountry", t.getTouristPlanCountry());
            touristPlan.put("adminId", t.getAdmin().getAdminId());

            touristPlanUser.put(t.getTouristPlanId().toString(), touristPlan);
        }
        return touristPlanUser;
    }

    public static Map<String, Object> convertDestinationsToMap(List<TouristDestination> TouristDestination){
        Map<String, Object> touristDestinationUser = new HashMap<>();
        for (TouristDestination t : TouristDestination) {
            Map<String, Object> touristDestination = new HashMap<>();
            touristDestination.put("touristDestinationId", t.getTouristDestinationId());
            touristDestination.put("touristDestinationCountry", t.getTouristDestinationCountry());
            touristDestination.put("touristDestinationState", t.getTouristDestinationState());
            touristDestinationUser.put(t.getTouristDestinationId().toString(), touristDestination);
        }
        return touristDestinationUser;
    }
}