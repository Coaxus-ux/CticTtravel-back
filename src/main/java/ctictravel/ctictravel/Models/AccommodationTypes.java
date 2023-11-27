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
@Table(name = "accommodation_types")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccommodationTypes {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "accommodation_type_id")
    private UUID accommodationTypeId;
    @Column(name = "accommodation_type_name" , nullable = false)
    private String accommodationTypeName;

    @OneToMany(mappedBy = "accommodationType")
    private List<Accommodations> accommodations;
}
