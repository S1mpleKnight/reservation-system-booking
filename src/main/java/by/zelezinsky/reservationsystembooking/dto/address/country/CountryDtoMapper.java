package by.zelezinsky.reservationsystembooking.dto.address.country;

import by.zelezinsky.reservationsystembooking.entity.address.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CountryDtoMapper {

    CountryDto toDto(Country country);

    Country toEntity(CountryDto dto);

    @Mapping(target = "id", ignore = true)
    Country toEntity(@MappingTarget Country country, CountryDto dto);
}
