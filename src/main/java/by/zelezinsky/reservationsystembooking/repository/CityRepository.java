package by.zelezinsky.reservationsystembooking.repository;

import by.zelezinsky.reservationsystembooking.entity.address.City;
import by.zelezinsky.reservationsystembooking.entity.address.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {

    Page<City> findAllByCounty_Id(UUID id, Pageable pageable);

    Boolean existsByNameAndCounty(String name, Country country);

    Optional<City> findByNameAndCounty(String name, Country country);
}
