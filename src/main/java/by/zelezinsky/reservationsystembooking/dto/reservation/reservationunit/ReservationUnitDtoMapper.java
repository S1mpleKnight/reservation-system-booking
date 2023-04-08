package by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit;

import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunittype.ReservationUnitTypeDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReservationUnitTypeDtoMapper.class)
public interface ReservationUnitDtoMapper {

    ReservationUnitDto toDto(ReservationUnit unit);

    @Mapping(target = "reservationUnitType", ignore = true)
    @Mapping(target = "offer", ignore = true)
    @Mapping(target = "reservation", ignore = true)
    @Mapping(target = "reservationUnitedPart")
    ReservationUnit toEntity(ReservationUnitDto dto);
}
