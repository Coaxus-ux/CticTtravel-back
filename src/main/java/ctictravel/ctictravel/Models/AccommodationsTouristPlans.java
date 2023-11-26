package ctictravel.ctictravel.Models;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accommodations_tourist_plans")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccommodationsTouristPlans {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "accommodations_tourist_plan_id")
    private UUID accommodationsTouristPlanId;

    @ManyToOne
    @JoinColumn(name = "accommodation_id", nullable = false)
    private Accommodations accommodation;

    @ManyToOne
    @JoinColumn(name = "tourist_plan_id", nullable = false)
    private TouristPlans touristPlan;
}
