package ctictravel.ctictravel.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Entity
@Table(name = "admins")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class,
        property = "adminId"
)
public class Admins {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "admin_id")
    private UUID adminId;
    @Column(name = "admin_name", nullable = false, length = 150)
    private String adminName;
    @Column(name = "admin_last_name", nullable = false, length = 150)
    private String adminLastName;
    @Column(name = "admin_email", nullable = false, length = 260, unique = true)
    private String adminEmail;
    @Column(name = "admin_password", nullable = false, length = 260)
    private String adminPassword;
    @Column(name = "admin_phone", nullable = false, length = 260)
    private String adminPhone;


    @OneToMany(mappedBy = "admin")
    private List<TouristPlans> touristPlans;

    public boolean hasEmptyNullFields() {
        return Stream.of(adminName, adminLastName, adminEmail, adminPassword, adminPhone)
                .anyMatch(field -> field == null || field.isEmpty());
    }

    public void updateAdmin(Admins admin) {
        this.adminName = admin.adminName;
        this.adminLastName = admin.adminLastName;
        this.adminEmail = admin.adminEmail;
        this.adminPassword = admin.adminPassword;
        this.adminPhone = admin.adminPhone;
    }

}
