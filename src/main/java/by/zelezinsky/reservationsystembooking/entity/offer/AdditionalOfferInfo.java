package by.zelezinsky.reservationsystembooking.entity.offer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
