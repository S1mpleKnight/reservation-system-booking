package by.zelezinsky.reservationsystembooking.dto.reservation;

import by.zelezinsky.reservationsystembooking.dto.user.user.UserDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.reservation.Reservation;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserDtoMapper.class})
public interface ReservationDtoMapper {

    String QUALIFIER_UNIT_IDS = "QUALIFIER_UNIT_IDS";
    String QUALIFIER_DATE_FORMATTER = "QUALIFIER_DATE_FORMATTER";
    String QUALIFIER_TIME_FORMATTER = "QUALIFIER_TIME_FORMATTER";

    @Mapping(target = "units", ignore = true)
    @Mapping(target = "unitIds", qualifiedByName = QUALIFIER_UNIT_IDS, source = "reservation")
    @Mapping(target = "reservationTime", qualifiedByName = QUALIFIER_TIME_FORMATTER, source = "reservation")
    @Mapping(target = "reservationDate", qualifiedByName = QUALIFIER_DATE_FORMATTER, source = "reservation")
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

    @Named(QUALIFIER_UNIT_IDS)
    default List<UUID> getUnitIds(Reservation reservation) {
        return reservation.getUnits().stream().map(ReservationUnit::getId).collect(Collectors.toList());
    }

    @Named(QUALIFIER_DATE_FORMATTER)
    default LocalDate getDate(Reservation reservation) {
        return reservation.getReservationDate();
    }

    @Named(QUALIFIER_TIME_FORMATTER)
    default LocalTime getTime(Reservation reservation) {
        return reservation.getReservationTime();
    }
}
