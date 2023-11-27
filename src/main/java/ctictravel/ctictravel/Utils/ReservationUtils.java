package ctictravel.ctictravel.Utils;

import ctictravel.ctictravel.Models.Reservations;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ReservationUtils {
    public static Map<String, Object> convertReservationsToMap(List<Reservations> existingReservations) {
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
            reservationUser.put("adminId", r.getTouristPlan().getAdmin().getAdminId());

            reservationsUser.put(r.getReservationId().toString(), reservationUser);
        }

        return reservationsUser;
    }
}