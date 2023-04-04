package by.zelezinsky.reservationsystembooking.entity.offer;

import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnit;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitedPart;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "reservation_offer")
@Entity
public class ReservationOffer {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false, insertable = false, updatable = false)
    private Event event;

    @Column(name = "event_id")
    private UUID eventId;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reservation_offer_offer_category",
            joinColumns = { @JoinColumn(name = "reservation_offer_id")},
            inverseJoinColumns = { @JoinColumn(name = "offer_category_id")}
    )
    private List<OfferCategory> categories;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "additional_info_id", nullable = false, updatable = false, insertable = false)
    private AdditionalOfferInfo additionalInfo;

    @Column(name = "additional_info_id", nullable = false)
    private UUID additionalInfoId;
}
