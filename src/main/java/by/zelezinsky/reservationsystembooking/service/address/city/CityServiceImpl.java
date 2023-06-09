package by.zelezinsky.reservationsystembooking.service.address.city;

import by.zelezinsky.reservationsystembooking.dto.address.city.CityDto;
import by.zelezinsky.reservationsystembooking.dto.address.city.CityDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.address.City;
import by.zelezinsky.reservationsystembooking.entity.address.Country;
import by.zelezinsky.reservationsystembooking.exception.BadRequestException;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.CityRepository;
import by.zelezinsky.reservationsystembooking.repository.CountryRepository;
import by.zelezinsky.reservationsystembooking.repository.establishment.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityDtoMapper cityDtoMapper;
    private final CountryRepository countryRepository;
    private final EstablishmentRepository establishmentRepository;

    @Override
    public Page<CityDto> findAll(Pageable pageable, UUID countryId) {
        if (Objects.isNull(pageable)) {
            pageable = Pageable.unpaged();
        }
        if (Objects.nonNull(countryId)) {
            return cityRepository.findAllByCountry_Id(countryId, pageable).map(cityDtoMapper::toDto);
        } else {
            return cityRepository.findAll(pageable).map(cityDtoMapper::toDto);
        }
    }

    @Override
    public CityDto create(CityDto dto) {
        Country country = findCountry(dto.getCountryId());
        Optional<City> optionalCity = cityRepository.findByNameAndCountry(dto.getName(), country);
        if (optionalCity.isPresent()) {
            throw new BadRequestException("Such city already exists");
        }
        City entity = cityDtoMapper.toEntity(dto);
        entity.setCountry(country);
        return cityDtoMapper.toDto(cityRepository.save(entity));
    }

    @Override
    public CityDto update(UUID id, CityDto dto) {
        City city = findCity(id);
        Country country = findCountry(dto.getCountryId());
        if (!city.getName().equals(dto.getName())) {
            if (cityRepository.existsByNameAndCountry(dto.getName(), country)) {
                throw new BadRequestException("Such city already exists");
            }
        }
        city = cityDtoMapper.toEntity(city, dto);
        city.setCountry(country);
        return cityDtoMapper.toDto(cityRepository.save(city));
    }

    @Override
    public void delete(UUID id) {
        City city = findCity(id);
        if (establishmentRepository.existsByCity(city)) {
            throw new BadRequestException("There are some establishments with this city");
        }
        cityRepository.delete(city);
    }

    private City findCity(UUID id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("City", id.toString()));
    }

    private Country findCountry(UUID id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Country", id.toString()));
    }
}
