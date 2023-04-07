package by.zelezinsky.reservationsystembooking.entity.reservation;

import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Table(name = "reservation_units")
@Entity
public class ReservationUnit {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_united_part_id", updatable = false, insertable = false)
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

    @Column(name = "reservation_time")
    private LocalTime time;

    @Column(name = "has_time", nullable = false)
    private Boolean hasTime;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "has_order_number", nullable = false)
    private Boolean hasOrderNumber;

    @Column(name = "has_reservation_unit_type", nullable = false)
    private Boolean hasReservationUnitType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_unit_type_id")
    private ReservationUnitType reservationUnitType;
}
