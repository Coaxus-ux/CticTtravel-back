package ctictravel.ctictravel.Models;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "tourist_plans")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TouristPlans {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tourist_plan_id")
    private UUID touristPlanId;
    @Column(name = "tourist_plan_name", nullable = false, length = 150)
    private String touristPlanName;
    @Column(name = "tourist_plan_description", nullable = false, length = 250)
    private String touristPlanDescription;
    @Column(name = "tourist_plan_price", nullable = false, length = 60)
    private BigDecimal touristPlanPrice;
    @Column(name = "tourist_plan_start", nullable = false, length = 160)
    private Date touristPlanStart;
    @Column(name = "tourist_plan_end", nullable = false, length = 160)
    private Date touristPlanEnd;
    @Column(name = "tourist_plan_places", nullable = false, length = 160)
    private String touristPlanPlaces;
    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "transport_method_id")
    private TransportMethods transportMethod;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admins admin;

    @OneToMany (mappedBy = "touristPlan")
    private List<Reservations> reservations;


    @OneToMany(mappedBy = "touristPlan")
    private List<TouristDestinationTouristPlans> touristDestinationTouristPlans;

    @OneToMany(mappedBy = "touristPlan")
    private List<AccommodationsTouristPlans> accommodationsTouristPlans;
}