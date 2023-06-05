package by.zelezinsky.reservationsystembooking.dto.offer.establishment;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import by.zelezinsky.reservationsystembooking.dto.address.city.CityDto;
import by.zelezinsky.reservationsystembooking.dto.address.country.CountryDto;
import by.zelezinsky.reservationsystembooking.dto.user.user.UserDto;
import jakarta.validation.Valid;
import lombok.Data;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;

@Data
@Valid
public class EstablishmentDto {

    private UUID id;

    @NotNull(message = "Contact can not be empty")
    private UUID contactId;

    @NotNull(message = "County flag can not be empty")
    private Boolean hasCountry;

    private UUID countryId;

    @NotNull(message = "City flag can not be empty")
    private Boolean hasCity;

    private UUID cityId;

    @NotNull(message = "Street flag can not be empty")
    private Boolean hasStreet;

    @Pattern(regexp = DtoConstants.CITY_AND_STREET_REGEXP, message = "Street name should be between 2 and 100 letters")
    private String street;

    @NotNull(message = "Building flag can not be empty")
    private Boolean hasBuilding;

    @Pattern(regexp = DtoConstants.BUILDING_REGEXP, message = "Building name should have numbers and letters, have " +
            "size from 1 to 5 symbols")
    private String building;

    @NotNull(message = "Apartment flag can not be empty")
    private Boolean hasApartment;

    @Pattern(regexp = DtoConstants.APARTMENT_REGEXP, message = "Apartment name should have up to 5 numbers and 1 " +
            "letter after")
    private String apartment;

    private CountryDto country;

    private CityDto city;

    private UserDto contact;
}
