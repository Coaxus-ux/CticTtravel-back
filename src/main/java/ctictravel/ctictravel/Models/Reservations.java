package ctictravel.ctictravel.Models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    private UUID reservationId;

    @Column(name = "reservation_date", nullable = false, length = 160)
    private Date reservationDate;
    @Column(name = "reservation_isActive", nullable = false)
    private Boolean reservationIsActive;
    @ManyToOne
    @JoinColumn(name = "tourist_plan_id", nullable = false)
    private TouristPlans touristPlan;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

}
