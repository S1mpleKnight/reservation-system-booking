package by.zelezinsky.reservationsystembooking.dto.offer.establishment;

import by.zelezinsky.reservationsystembooking.dto.address.city.CityDtoMapper;
import by.zelezinsky.reservationsystembooking.dto.address.country.CountryDtoMapper;
import by.zelezinsky.reservationsystembooking.dto.user.user.UserDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.Establishment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserDtoMapper.class, CityDtoMapper.class, CountryDtoMapper.class})
public interface EstablishmentDtoMapper {

    EstablishmentDto toDto(Establishment establishment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contact", ignore = true)
    @Mapping(target = "contactId", ignore = true)
    @Mapping(target = "offers", ignore = true)
    Establishment toEntity(EstablishmentDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contact", ignore = true)
    @Mapping(target = "contactId", ignore = true)
    @Mapping(target = "offers", ignore = true)
    Establishment toEntity(@MappingTarget Establishment establishment, EstablishmentDto dto);
}
