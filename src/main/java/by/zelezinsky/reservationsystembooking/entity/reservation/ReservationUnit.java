package by.zelezinsky.reservationsystembooking.entity.reservation;

import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Table(name = "reservation_units")
@Entity
@ToString
public class ReservationUnit {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "description")
    private String description;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "has_united_part", nullable = false)
    private Boolean hasUnitedPart;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "reservation_united_part_id", updatable = false, insertable = false)
    private ReservationUnitedPart reservationUnitedPart;

    @Column(name = "reservation_united_part_id")
    private UUID reservationUnitedPartId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_offer_id", nullable = false, updatable = false, insertable = false)
    private ReservationOffer offer;

    @Column(name = "reservation_offer_id", nullable = false)
    private UUID offerId;

    @Column(name = "has_time", nullable = false)
    private Boolean hasTime;

    @Column(name = "reservation_time")
    private LocalTime time;

    @Column(name = "has_order_number", nullable = false)
    private Boolean hasOrderNumber;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "has_reservation_unit_type", nullable = false)
    private Boolean hasReservationUnitType;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_unit_type_id", insertable = false, updatable = false)
    private ReservationUnitType reservationUnitType;

    @Column(name = "reservation_unit_type_id")
    private UUID reservationUnitTypeId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", updatable = false, insertable = false)
    private Reservation reservation;

    @Column(name = "reservation_id")
    private UUID reservationId;
}
