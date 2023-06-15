package by.zelezinsky.reservationsystembooking.entity.offer;

import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnit;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitType;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitedPart;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "reservation_offers")
@Entity
@ToString
public class ReservationOffer {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "has_event", nullable = false)
    private Boolean hasEvent;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offer")
    private List<ReservationUnit> reservationUnits;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offer")
    private List<ReservationUnitedPart> unitedParts;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name = "has_time", nullable = false)
    private Boolean hasTime;

    @Column(name = "reservation_time")
    private LocalTime reservationTime;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reservation_offer_offer_category",
            joinColumns = { @JoinColumn(name = "reservation_offer_id")},
            inverseJoinColumns = { @JoinColumn(name = "offer_category_id")}
    )
    private List<OfferCategory> categories;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_type", nullable = false)
    private ReservationType reservationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false)
    private OrderReservationType orderType;

    @Enumerated(EnumType.STRING)
    @Column(name = "offer_status", nullable = false)
    private ReservationOfferStatus offerStatus;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User contact;

    @Column(name = "has_establishment", nullable = false)
    private Boolean hasEstablishment;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;


    @Column(name = "has_additional_info", nullable = false)
    private Boolean hasAdditionalInfo;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "offer")
    private AdditionalOfferInfo info;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offer")
    private List<ReservationUnitType> types;
}
