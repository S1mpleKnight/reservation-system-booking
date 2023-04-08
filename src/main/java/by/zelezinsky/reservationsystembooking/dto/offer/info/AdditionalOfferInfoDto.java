package by.zelezinsky.reservationsystembooking.dto.offer.info;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class AdditionalOfferInfoDto {

    private UUID id;

    private String imageUrl;

    private String establishmentUrl;

    private String eventUrl;

    @NotNull(message = "Offer can not be empty")
    private UUID offerId;
}
