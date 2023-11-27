package ctictravel.ctictravel.Models;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Entity
@Table(name = "transport_methods")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransportMethods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transport_method_id")
    private UUID transportMethodId;
    @Column(name = "transport_method_type", nullable = false, length = 60)
    private String transportMethodType;
    @OneToMany(mappedBy = "transportMethod")
    @ToString.Exclude
    private List<TouristPlans> touristPlans;

    public boolean hasEmptyFields() {
        return transportMethodType == null || transportMethodType.isEmpty();
    }

    public void updateTransportMethod(TransportMethods transportMethods) {
        this.transportMethodType = transportMethods.transportMethodType;
    }
}
