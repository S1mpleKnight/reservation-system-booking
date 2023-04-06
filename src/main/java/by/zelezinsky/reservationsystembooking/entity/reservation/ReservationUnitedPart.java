package by.zelezinsky.reservationsystembooking.entity.reservation;

import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "reservation_united_parts")
@Entity
public class ReservationUnitedPart {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "has_time")
    private Boolean hasTime;

    @Column(name = "reservation_time")
    private LocalTime time;

    @OneToMany(mappedBy = "reservationUnitedPart", fetch = FetchType.LAZY)
    private List<ReservationUnit> units;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ReservationUnitedPart parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_offer_id", insertable = false, updatable = false, nullable = false)
    private ReservationOffer offer;

    @Column(name = "reservation_offer_id")
    private UUID offerId;
}
