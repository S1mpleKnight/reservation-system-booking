package by.zelezinsky.reservationsystembooking.dto.filter;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EstablishmentFilter {
    private List<UUID> contactIds;
    private Boolean hasCity;
    private List<UUID> cityIds;
    private Boolean hasCountry;
    private List<UUID> countryIds;
    private String hasBuilding;
    private Boolean hasApartment;
    private String hasStreet;
}
