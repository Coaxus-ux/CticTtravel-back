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
@Table(name = "tourist_destinations")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TouristDestination {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "tourist_destination_id")
    private UUID touristDestinationId;
    @Column(name = "tourist_destination_country", nullable = false, length = 150)
    private String touristDestinationCountry;
    @Column(name = "tourist_destination_city", nullable = false, length = 150)
    private String touristDestinationCity;

    @OneToMany(mappedBy = "touristDestination")
    private List<AtractivePlaces> atractivePlaces;

    @OneToMany(mappedBy = "touristDestination")
    private List<TouristDestinationTouristPlans> touristDestinationTouristPlans;
}
