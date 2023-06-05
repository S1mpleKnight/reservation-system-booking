package by.zelezinsky.reservationsystembooking.service.offer.establishment;

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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstablishmentServiceImpl implements EstablishmentService {

    private final EstablishmentDtoMapper establishmentDtoMapper;
    private final EstablishmentRepository establishmentRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Override
    @Transactional
    public EstablishmentDto create(EstablishmentDto dto) {
        User user = findUser(dto.getContactId());
        City city = findCity(dto.getCityId());
        if (dto.getHasCity() && Objects.isNull(city)) {
            throw new BadRequestException("City can not be empty");
        }
        Country country = findCountry(dto.getCountryId());
        if (dto.getHasCountry() && Objects.isNull(country)) {
            throw new BadRequestException("Country can not be empty");
        }
        if (dto.getHasCountry() && dto.getHasCity() && !city.getCountry().equals(country)) {
            throw new BadRequestException("This city do not belongs to the country");
        }
        if (establishmentRepository.findByCountryAndCityAndStreetAndBuildingAndApartment(country, city,
                dto.getStreet(), dto.getBuilding(), dto.getApartment()).isPresent()) {
            throw new BadRequestException("Such an establishment is already exists");
        }
        Establishment entity = establishmentDtoMapper.toEntity(dto);
        entity.setCity(city);
        entity.setCountry(country);
        entity.setContact(user);
        return establishmentDtoMapper.toDto(establishmentRepository.save(entity));
    }

    @Override
    @Transactional
    public EstablishmentDto update(UUID id, EstablishmentDto dto) {
        Establishment establishment = findEstablishment(id);
        User user = findUser(dto.getContactId());
        City city = findCity(dto.getCityId());
        if (dto.getHasCity() && Objects.isNull(city)) {
            throw new BadRequestException("City can not be empty");
        }
        Country country = findCountry(dto.getCountryId());
        if (dto.getHasCountry() && Objects.isNull(country)) {
            throw new BadRequestException("Country can not be empty");
        }
        if (dto.getHasCountry() && dto.getHasCity() && !city.getCountry().equals(country)) {
            throw new BadRequestException("This city do not belongs to the country");
        }
        if (establishmentRepository.findByCountryAndCityAndStreetAndBuildingAndApartment(country, city,
                dto.getStreet(), dto.getBuilding(), dto.getApartment()).isPresent()) {
            throw new BadRequestException("Such an establishment is already exists");
        }
        establishment = establishmentDtoMapper.toEntity(establishment, dto);
        establishment.setCity(city);
        establishment.setCountry(country);
        establishment.setContact(user);
        return establishmentDtoMapper.toDto(establishmentRepository.save(establishment));
    }

    @Override
    public EstablishmentDto findById(UUID id) {
        return establishmentDtoMapper.toDto(findEstablishment(id));
    }

    @Override
    public Page<EstablishmentDto> findAll(Pageable pageable, EstablishmentFilter filter) {
        if (Objects.isNull(pageable)) {
            pageable = Pageable.unpaged();
        }
        Page<Establishment> all = establishmentRepository.findAll(pageable, filter);
        return all.map(establishmentDtoMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        Establishment establishment = findEstablishment(id);
        establishmentRepository.delete(establishment);
    }

    private City findCity(UUID id) {
        return Objects.isNull(id) ? null : cityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("City", id.toString()));
    }

    private Country findCountry(UUID id) {
        return Objects.isNull(id) ? null : countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Country", id.toString()));
    }

    private Establishment findEstablishment(UUID id) {
        return establishmentRepository.findById(id).orElseThrow(() -> new NotFoundException("User", id.toString()));
    }

    private User findUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", id.toString()));
    }
}
