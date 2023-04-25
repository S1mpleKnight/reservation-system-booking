package by.zelezinsky.reservationsystembooking.service.reservation.unit;

import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit.ReservationUnitDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReservationUnitService {

    ReservationUnitDto findById(UUID id, UUID uuid);

    Page<ReservationUnitDto> findAll(UUID id, Pageable pageable);

    ReservationUnitDto create(UUID id, ReservationUnitDto dto);

    ReservationUnitDto update(UUID id, UUID uuid, ReservationUnitDto dto);

    void delete(UUID id, UUID uuid);
}
