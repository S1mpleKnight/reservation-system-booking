package by.zelezinsky.reservationsystembooking.dto.reservation;

import by.zelezinsky.reservationsystembooking.dto.user.user.UserDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.reservation.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserDtoMapper.class})
public interface ReservationDtoMapper {

    @Mapping(target = "unitIds", ignore = true)
    ReservationDto toDto(Reservation reservation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "units", ignore = true)
    @Mapping(target = "reservationDate", ignore = true)
    @Mapping(target = "reservationTime", ignore = true)
    Reservation toEntity(ReservationDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "units", ignore = true)
    @Mapping(target = "reservationDate", ignore = true)
    @Mapping(target = "reservationTime", ignore = true)
    Reservation toEntity(@MappingTarget Reservation reservation, ReservationDto dto);
}
