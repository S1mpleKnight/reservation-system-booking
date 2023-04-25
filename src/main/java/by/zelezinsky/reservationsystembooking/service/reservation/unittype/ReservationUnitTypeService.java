package by.zelezinsky.reservationsystembooking.service.reservation.unittype;

import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunittype.ReservationUnitTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReservationUnitTypeService {

    ReservationUnitTypeDto create(UUID offerId, ReservationUnitTypeDto dto);

    ReservationUnitTypeDto update(UUID offerId, UUID id, ReservationUnitTypeDto dto);

    ReservationUnitTypeDto findById(UUID id, UUID uuid);

    Page<ReservationUnitTypeDto> findAll(UUID id, Pageable pageable);

    void delete(UUID id, UUID uuid);
}
