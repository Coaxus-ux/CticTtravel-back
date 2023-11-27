package ctictravel.ctictravel.Models;

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
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "user_id")
    private UUID userId;
    @Column(name = "user_name", nullable = false, length = 150)
    private String userName;
    @Column(name = "user_last_name", nullable = false, length = 150)
    private String userLastName;
    @Column(name = "user_email", nullable = false, length = 260)
    private String userEmail;
    @Column(name = "user_password", nullable = false, length = 260)
    private String userPassword;
    @Column(name = "user_phone", nullable = false, length = 260)
    private String userPhone;
    @Column(name = "user_address", nullable = false, length = 260)
    private String userAddress;
    @Column(name = "user_city", nullable = false, length = 260)
    private String userCity;
    @Column(name = "user_country", nullable = false, length = 260)
    private String userCountry;
    @Column(name = "user_state", nullable = false, length = 260)
    private String userState;

    @OneToMany(mappedBy = "user")
    private List<Reservations> reservations;

    public boolean hasEmptyNullFields() {
        return Stream.of(userName, userLastName, userEmail, userPassword, userPhone, userAddress, userCity, userCountry, userState)
                .anyMatch(field -> field == null || field.isEmpty());
    }
}
