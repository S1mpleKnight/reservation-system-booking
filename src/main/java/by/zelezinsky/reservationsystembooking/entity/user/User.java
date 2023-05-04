package by.zelezinsky.reservationsystembooking.entity.user;

import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import by.zelezinsky.reservationsystembooking.entity.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Reservation> reservations;

    @Column(name = "is_male", nullable = false)
    private Boolean isMale;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
    private List<ReservationOffer> offers;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.getRole().getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .toList();
    }
}
