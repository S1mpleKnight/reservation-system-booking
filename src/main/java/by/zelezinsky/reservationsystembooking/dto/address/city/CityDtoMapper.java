package by.zelezinsky.reservationsystembooking.dto.address.city;

import by.zelezinsky.reservationsystembooking.dto.address.country.CountryDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.address.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = CountryDtoMapper.class)
public interface CityDtoMapper {

    @Mapping(target = "countryId", ignore = true)
    CityDto toDto(City city);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", ignore = true)
    City toEntity(CityDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", ignore = true)
    City toEntity(@MappingTarget City city, CityDto dto);
}
