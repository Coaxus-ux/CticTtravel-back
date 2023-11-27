package ctictravel.ctictravel.Models;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accommodations")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Accommodations {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "accommodation_id")
    private UUID accommodationId;
    @Column(name = "accommodation_name" , nullable = false)
    private String accommodationName;
    @Column(name = "accommodation_rooms_quantity" , nullable = false)
    private Integer accommodationRoomsQuantity;
    @Column(name = "accommodation_address" , nullable = false)
    private String accommodationAddress;
    @Column(name = "accommodation_price" , nullable = false)
    private BigDecimal accommodationPrice;
    @Column(name = "accommodation_check-in_schedule" , nullable = false)
    private String accommodationCheckInSchedule;
    @Column(name = "accommodation_check-out_schedule" , nullable = false)
    private String accommodationCheckOutSchedule;
    @Column(name = "accommodation_country" , nullable = false)
    private String accommodationCountry;
    @Column(name = "accommodation_city" , nullable = false)
    private String accommodationCity;

    @ManyToOne
    @JoinColumn(name = "accommodation_type_id", nullable = false)
    private AccommodationTypes accommodationType;

    @OneToMany(mappedBy = "accommodation")
    private List<AccommodationsTouristPlans> accommodationsTouristPlans;
}
