package by.zelezinsky.reservationsystembooking.entity.reservation;

import by.zelezinsky.reservationsystembooking.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "reservations")
@Entity
public class Reservation {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    private User user;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name = "reservation_time", nullable = false)
    private LocalTime reservationTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReservationStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
    private List<ReservationUnit> units;
}
