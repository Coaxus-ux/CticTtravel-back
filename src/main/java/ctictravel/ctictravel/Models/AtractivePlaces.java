package ctictravel.ctictravel.Models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "atractive_places")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AtractivePlaces {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "atractive_place_id")
    private UUID atractivePlaceId;
    @Column(name = "atractive_place_name", nullable = false, length = 150)
    private String atractivePlaceName;
    @Column(name = "atractive_place_description", nullable = false, length = 350)
    private String atractivePlaceDescription;
    @Column(name = "atractive_place_country", nullable = false, length = 150)
    private String atractivePlaceCountry;
    @Column(name = "atractive_place_state", nullable = false, length = 150)
    private String atractivePlaceState;
    @Column(name = "atractive_place_city", nullable = false, length = 150)
    private String atractivePlaceCity;

    @ManyToOne
    @JoinColumn(name = "tourist_destination_id", nullable = false)
    private TouristDestination touristDestination;

    public boolean hasEmptyNullFields() {
        return atractivePlaceName == null || atractivePlaceName.isEmpty() || atractivePlaceDescription == null || atractivePlaceDescription.isEmpty() || atractivePlaceCountry == null || atractivePlaceCountry.isEmpty() || atractivePlaceState == null || atractivePlaceState.isEmpty() || atractivePlaceCity == null || atractivePlaceCity.isEmpty();
    }

    public void updateAtractivePlace(AtractivePlaces atractivePlace) {
        this.atractivePlaceName = atractivePlace.atractivePlaceName;
        this.atractivePlaceDescription = atractivePlace.atractivePlaceDescription;
        this.atractivePlaceCountry = atractivePlace.atractivePlaceCountry;
        this.atractivePlaceState = atractivePlace.atractivePlaceState;
        this.atractivePlaceCity = atractivePlace.atractivePlaceCity;
    }

}
