package by.zelezinsky.reservationsystembooking.dto.offer.category;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@Valid
public class OfferCategoryDto {

    private UUID id;

    @NotBlank(message = "Name can not be empty")
    @Pattern(regexp = DtoConstants.NAME_REGEXP, message = "Name should be between 1 and 50 letters")
    private String name;
}
