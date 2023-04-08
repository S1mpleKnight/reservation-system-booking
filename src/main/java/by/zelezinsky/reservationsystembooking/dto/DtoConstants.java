package by.zelezinsky.reservationsystembooking.dto;

public interface DtoConstants {

    String USERNAME_REGEXP = "[a-zA-Z[0-9]]{8,50}";
    String NAME_REGEXP = "[a-zA-Z]{1,50}";
    String DATE_REGEXP = "yyyy-MM-dd";
    String TIME_REGEXP = "HH:MM";
    String CITY_AND_STREET_REGEXP = "[a-zA-Z[-]]{2,100}";
    String COUNTRY_REGEXP = "[a-zA-Z]{2,60}";
    String BUILDING_REGEXP = "[a-zA-Z[0-9]]{1,5}";
    String APARTMENT_REGEXP = "[0-9]{1,5}([a-zA-Z]{1})?";
    String DESCRIPTION_REGEXP = "[a-zA-Z[0-9] ]{0,255}";
}
