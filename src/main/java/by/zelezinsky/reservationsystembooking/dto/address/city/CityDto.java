package by.zelezinsky.reservationsystembooking.dto.address.city;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import by.zelezinsky.reservationsystembooking.dto.address.country.CountryDto;
import lombok.Data;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;

@Data
public class CityDto {

    private UUID id;

    @NotNull(message = "City name can not be empty")
    @Pattern(regexp = DtoConstants.CITY_AND_STREET_REGEXP, message = "Name should be between 2 and 100 letters")
    private String name;

    @NotNull(message = "Country can not be empty")
    private UUID countryId;

    private CountryDto country;
}
