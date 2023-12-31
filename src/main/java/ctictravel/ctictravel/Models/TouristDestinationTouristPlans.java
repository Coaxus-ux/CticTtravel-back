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
@Table(name = "tourist_destination_tourist_plans")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TouristDestinationTouristPlans {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "tourist_destination_tourist_plan_id")
    private UUID touristDestinationTouristPlanId;

    @ManyToOne
    @JoinColumn(name = "tourist_destination_id", nullable = false)
    private TouristDestination touristDestination;

    @ManyToOne
    @JoinColumn(name = "tourist_plan_id", nullable = false)
    private TouristPlans touristPlan;

    public boolean hasEmptyNullFields(){
        return touristDestination == null || touristPlan == null || touristPlan.getTouristPlanId().toString().isEmpty() || touristDestination.getTouristDestinationId().toString().isEmpty();

    }
}
