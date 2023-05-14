package by.zelezinsky.reservationsystembooking.repository.establishment;

import by.zelezinsky.reservationsystembooking.entity.address.City;
import by.zelezinsky.reservationsystembooking.entity.address.Country;
import by.zelezinsky.reservationsystembooking.entity.offer.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, UUID>, QEstablishmentRepository {

    Boolean existsByCountryAndCityAndStreetAndBuildingAndApartment(
            Country country, City city, String street, String building, String apartment
    );

    Boolean existsByCity(City city);
}
