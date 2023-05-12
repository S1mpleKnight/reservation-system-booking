package by.zelezinsky.reservationsystembooking.service.address.country;

import by.zelezinsky.reservationsystembooking.dto.address.country.CountryDto;
import by.zelezinsky.reservationsystembooking.dto.address.country.CountryDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.address.Country;
import by.zelezinsky.reservationsystembooking.exception.BadRequestException;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.CityRepository;
import by.zelezinsky.reservationsystembooking.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryDtoMapper countryDtoMapper;
    private final CityRepository cityRepository;

    @Override
    public Page<CountryDto> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable).map(countryDtoMapper::toDto);
    }

    @Override
    public CountryDto create(CountryDto dto) {
        Optional<Country> optionalCountry = countryRepository.findByName(dto.getName());
        if (optionalCountry.isPresent()) {
            return countryDtoMapper.toDto(optionalCountry.get());
        }
        Country entity = countryDtoMapper.toEntity(dto);
        return countryDtoMapper.toDto(countryRepository.save(entity));
    }

    @Override
    public CountryDto update(UUID id, CountryDto dto) {
        if (countryRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Country with that name already exists");
        }
        Country country = findCountry(id);
        country = countryDtoMapper.toEntity(country, dto);
        return countryDtoMapper.toDto(countryRepository.save(country));
    }

    @Override
    public void delete(UUID id) {
        Country country = findCountry(id);
        if (cityRepository.existsByCountry(country)) {
            throw new BadRequestException("Country can not be deleted, there are some cities");
        }
        countryRepository.delete(country);
    }

    private Country findCountry(UUID id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Country", id.toString()));
    }
}
