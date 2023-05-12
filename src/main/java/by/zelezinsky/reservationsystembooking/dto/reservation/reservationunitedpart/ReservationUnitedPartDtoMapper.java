package by.zelezinsky.reservationsystembooking.dto.reservation.reservationunitedpart;

import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitedPart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationUnitedPartDtoMapper {

    @Mapping(target = "unitIds", ignore = true)
    @Mapping(target = "parts", ignore = true)
    ReservationUnitedPartDto toDto(ReservationUnitedPart unitedPart);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "offer", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "units", ignore = true)
    ReservationUnitedPart toEntity(ReservationUnitedPartDto dto);
}
