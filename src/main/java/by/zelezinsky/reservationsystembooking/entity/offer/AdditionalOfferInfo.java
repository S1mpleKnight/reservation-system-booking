package by.zelezinsky.reservationsystembooking.entity.offer;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "additional_offer_info")
public class AdditionalOfferInfo {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "establishment_url")
    private String establishmentUrl;

    @Column(name = "event_url")
    private String eventUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", nullable = false, insertable = false, updatable = false)
    private ReservationOffer offer;

    @Column(name = "offer_id", nullable = false)
    private UUID offerId;
}
