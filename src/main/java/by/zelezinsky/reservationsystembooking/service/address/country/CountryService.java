package by.zelezinsky.reservationsystembooking.service.address.country;

import by.zelezinsky.reservationsystembooking.dto.address.country.CountryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CountryService {

    Page<CountryDto> findAll(Pageable pageable);

    CountryDto create(CountryDto dto);

    CountryDto update(UUID id, CountryDto dto);

    void delete(UUID id);
}
