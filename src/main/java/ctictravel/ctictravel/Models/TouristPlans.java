package ctictravel.ctictravel.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "touristPlanId"
)
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
    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "tourist_plan_country", nullable = false ,length = 150)
    private String touristPlanCountry;

    @ManyToOne
    @JoinColumn(name = "transport_method_id")
    @ToString.Exclude
    private TransportMethods transportMethod;

    @ManyToOne

    @JoinColumn(name = "admin_id")
    private Admins admin;

    @OneToMany (mappedBy = "touristPlan")
    @ToString.Exclude
    private List<Reservations> reservations;


    @OneToMany(mappedBy = "touristPlan")
    @ToString.Exclude
    private List<TouristDestinationTouristPlans> touristDestinationTouristPlans;

    @OneToMany(mappedBy = "touristPlan")
    @ToString.Exclude
    private List<AccommodationsTouristPlans> accommodationsTouristPlans;

    public boolean hasEmptyFields() {
        return touristPlanName == null || touristPlanName.isEmpty() ||
                touristPlanDescription == null || touristPlanDescription.isEmpty() ||
                touristPlanPrice == null || touristPlanPrice.compareTo(BigDecimal.ZERO) < 0 ||
                touristPlanStart == null || touristPlanStart.after(touristPlanEnd) ||
                touristPlanEnd == null || touristPlanEnd.before(touristPlanStart) ||
                touristPlanCountry == null || touristPlanCountry.isEmpty() ||
                transportMethod == null || admin == null;
    }
    public void updateTouristPlan(TouristPlans touristPlan) {
        this.touristPlanName = touristPlan.getTouristPlanName();
        this.touristPlanDescription = touristPlan.getTouristPlanDescription();
        this.touristPlanPrice = touristPlan.getTouristPlanPrice();
        this.touristPlanStart = touristPlan.getTouristPlanStart();
        this.touristPlanEnd = touristPlan.getTouristPlanEnd();
        this.transportMethod = touristPlan.getTransportMethod();
    }
    public void desactivateTouristPlan() {
        this.isAvailable = false;
    }
    public void activateTouristPlan() {
        this.isAvailable = true;
    }
}
