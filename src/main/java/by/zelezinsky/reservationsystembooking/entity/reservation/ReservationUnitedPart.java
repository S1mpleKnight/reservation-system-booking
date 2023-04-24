package by.zelezinsky.reservationsystembooking.entity.reservation;

import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import jakarta.persistence.*;
import lombok.Data;

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

    @OneToMany(mappedBy = "reservationUnitedPart", fetch = FetchType.LAZY)
    private List<ReservationUnit> units;

    @Column(name = "has_parent", nullable = false)
    private Boolean hasParent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private ReservationUnitedPart parent;

    @Column(name = "parent_id")
    private UUID parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_offer_id", insertable = false, updatable = false, nullable = false)
    private ReservationOffer offer;

    @Column(name = "reservation_offer_id")
    private UUID offerId;
}
