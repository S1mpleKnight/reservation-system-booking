package by.zelezinsky.reservationsystembooking.service.address.city;

import by.zelezinsky.reservationsystembooking.dto.address.city.CityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CityService {

    Page<CityDto> findAll(Pageable pageable, UUID countryId);

    CityDto create(CityDto dto);

    CityDto update(UUID id, CityDto dto);

    void delete(UUID id);
}
