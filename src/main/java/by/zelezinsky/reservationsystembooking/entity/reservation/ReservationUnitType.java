package by.zelezinsky.reservationsystembooking.entity.reservation;

import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "reservation_unit_types")
public class ReservationUnitType {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "reservationUnitType", fetch = FetchType.LAZY)
    private List<ReservationUnit> units;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_offer_id", nullable = false, insertable = false, updatable = false)
    private ReservationOffer offer;

    @Column(name = "reservation_offer_id", nullable = false)
    private UUID offerId;
}
