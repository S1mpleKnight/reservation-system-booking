package by.zelezinsky.reservationsystembooking.dto.offer.establishment;

import by.zelezinsky.reservationsystembooking.dto.user.user.UserDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.Establishment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserDtoMapper.class)
public interface EstablishmentDtoMapper {

    EstablishmentDto toDto(Establishment establishment);

    @Mapping(target = "events", ignore = true)
    Establishment toEntity(EstablishmentDto dto);
}
