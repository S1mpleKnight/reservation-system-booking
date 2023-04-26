package by.zelezinsky.reservationsystembooking.service.offer.establishment;

import by.zelezinsky.reservationsystembooking.dto.address.city.CityDtoMapper;
import by.zelezinsky.reservationsystembooking.dto.address.country.CountryDtoMapper;
import by.zelezinsky.reservationsystembooking.dto.filter.EstablishmentFilter;
import by.zelezinsky.reservationsystembooking.dto.offer.establishment.EstablishmentDto;
import by.zelezinsky.reservationsystembooking.dto.offer.establishment.EstablishmentDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.address.City;
import by.zelezinsky.reservationsystembooking.entity.address.Country;
import by.zelezinsky.reservationsystembooking.entity.offer.Establishment;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import by.zelezinsky.reservationsystembooking.exception.BadRequestException;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.CityRepository;
import by.zelezinsky.reservationsystembooking.repository.CountryRepository;
import by.zelezinsky.reservationsystembooking.repository.establishment.EstablishmentRepository;
import by.zelezinsky.reservationsystembooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstablishmentServiceImpl implements EstablishmentService {

    private final EstablishmentDtoMapper establishmentDtoMapper;
    private final EstablishmentRepository establishmentRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final CityDtoMapper cityDtoMapper;
    private final CountryRepository countryRepository;
    private final CountryDtoMapper countryDtoMapper;

    @Override
    @Transactional
    public EstablishmentDto create(EstablishmentDto dto) {
        User user = findUser(dto.getContactId());
        City city = getCity(dto);
        Country country = getCountry(dto);
        if (establishmentRepository.existsByCountryAndCityAndStreetAndBuildingAndApartment(country, city,
                dto.getStreet(), dto.getBuilding(), dto.getApartment())) {
            throw new BadRequestException("Such an establishment is already exists");
        }
        Establishment entity = establishmentDtoMapper.toEntity(dto);
        entity.setContactId(user.getId());
        return establishmentDtoMapper.toDto(establishmentRepository.save(entity));
    }

    @Override
    @Transactional
    public EstablishmentDto update(UUID id, EstablishmentDto dto) {
        Establishment establishment = findEstablishment(id);
        User user = findUser(dto.getContactId());
        City city = getCity(dto);
        Country country = getCountry(dto);
        if (establishmentRepository.existsByCountryAndCityAndStreetAndBuildingAndApartment(country, city,
                dto.getStreet(), dto.getBuilding(), dto.getApartment())) {
            throw new BadRequestException("Such an establishment is already exists");
        }
        establishment = establishmentDtoMapper.toEntity(establishment, dto);
        establishment.setContactId(user.getId());
        return establishmentDtoMapper.toDto(establishmentRepository.save(establishment));
    }

    @Override
    public EstablishmentDto findById(UUID id) {
        return establishmentDtoMapper.toDto(findEstablishment(id));
    }

    @Override
    public Page<EstablishmentDto> findAll(Pageable pageable, EstablishmentFilter filter) {
        return establishmentRepository.findAll(pageable, filter).map(establishmentDtoMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        Establishment establishment = findEstablishment(id);
        establishmentRepository.delete(establishment);
    }

    private City findCity(UUID id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("City", id.toString()));
    }

    private Country findCountry(UUID id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Country", id.toString()));
    }

    private Establishment findEstablishment(UUID id) {
        return establishmentRepository.findById(id).orElseThrow(() -> new NotFoundException("User", id.toString()));
    }

    private User findUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", id.toString()));
    }

    private Country getCountry(EstablishmentDto dto) {
        Country country;
        if (Objects.nonNull(dto.getCountryId())) {
            country = findCountry(dto.getCountryId());
        } else {
            country = countryDtoMapper.toEntity(dto.getCountry());
            Optional<Country> optionalCountry = countryRepository.findByName(dto.getCountry().getName());
            if (optionalCountry.isPresent()) {
                return optionalCountry.get();
            }
            country = countryRepository.save(country);
        }
        return country;
    }

    private City getCity(EstablishmentDto dto) {
        City city;
        if (Objects.nonNull(dto.getCityId())) {
            city = findCity(dto.getCityId());
        } else {
            city = cityDtoMapper.toEntity(dto.getCity());
            city = cityRepository.save(city);
        }
        return city;
    }
}
