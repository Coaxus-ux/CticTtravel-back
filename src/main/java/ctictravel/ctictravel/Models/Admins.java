package ctictravel.ctictravel.Models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "admins")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Admins {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "admin_id")
    private UUID adminId;
    @Column(name = "admin_name", nullable = false, length = 150)
    private String adminName;
    @Column(name = "admin_last_name", nullable = false, length = 150)
    private String adminLastName;
    @Column(name = "admin_email", nullable = false, length = 260)
    private String adminEmail;
    @Column(name = "admin_password", nullable = false, length = 260)
    private String adminPassword;
    @Column(name = "admin_phone", nullable = false, length = 260)
    private String adminPhone;
    @OneToMany(mappedBy = "admin")
    private java.util.List<TouristPlans> touristPlans;

}
