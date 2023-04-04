package by.zelezinsky.reservationsystembooking.entity.reservation;

import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Table(name = "reservation_unit")
@Entity
public class ReservationUnit {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ReservationUnitType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_united_part_id")
    private ReservationUnitedPart reservationUnitedPart;

    @Column(name = "reservation_united_part_id")
    private UUID reservationUnitedPartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false, updatable = false, insertable = false)
    private Reservation reservation;

    @Column(name = "reservation_id", nullable = false)
    private UUID reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_offer_id", updatable = false, insertable = false)
    private ReservationOffer offer;

    @Column(name = "reservation_offer_id")
    private UUID offerId;
}
