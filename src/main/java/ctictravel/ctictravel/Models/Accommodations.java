package ctictravel.ctictravel.Models;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

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

    @Column(name = "accommodation_address" , nullable = false)
    private String accommodationAddress;
    @Column(name = "accommodation_price" , nullable = false)
    private BigDecimal accommodationPrice;
    @Column(name = "accommodation_check-in_schedule" , nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime accommodationCheckInSchedule;
    @Column(name = "accommodation_check-out_schedule" , nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime accommodationCheckOutSchedule;
    @Column(name = "accommodation_country" , nullable = false)
    private String accommodationCountry;
    @Column(name = "accommodation_city" , nullable = false)
    private String accommodationCity;
    @Column(name = "accommodation_state" , nullable = false)
    private String accommodationState;

    @ManyToOne
    @JoinColumn(name = "accommodation_type_id", nullable = false)
    private AccommodationTypes accommodationType;

    @OneToMany(mappedBy = "accommodation")
    private List<AccommodationsTouristPlans> accommodationsTouristPlans;

    public boolean hasEmptyNullFields() {
        return Stream.of(accommodationName,accommodationState, accommodationAddress, accommodationPrice, accommodationCheckInSchedule, accommodationCheckOutSchedule, accommodationCountry, accommodationCity, accommodationType.getAccommodationTypeId()).anyMatch(field -> field == null || ((Serializable) field).equals(""));
    }
    public void updateAccommodations(Accommodations accommodations) {
        this.accommodationName = accommodations.accommodationName;
        this.accommodationAddress = accommodations.accommodationAddress;
        this.accommodationPrice = accommodations.accommodationPrice;
        this.accommodationCheckInSchedule = accommodations.accommodationCheckInSchedule;
        this.accommodationCheckOutSchedule = accommodations.accommodationCheckOutSchedule;
        this.accommodationCountry = accommodations.accommodationCountry;
        this.accommodationCity = accommodations.accommodationCity;
        this.accommodationType = accommodations.accommodationType;
        this.accommodationState = accommodations.accommodationState;
    }

}
