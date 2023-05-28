package by.zelezinsky.reservationsystembooking.dto.address.country;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import lombok.Data;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;

@Data
public class CountryDto {

    private UUID id;

    @NotNull(message = "Country name can not be null")
    @Pattern(regexp = DtoConstants.COUNTRY_REGEXP, message = "County should be between 2 and 60 letters")
    private String name;
}
