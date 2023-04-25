package by.zelezinsky.reservationsystembooking.service.reservation;

import by.zelezinsky.reservationsystembooking.dto.reservation.ReservationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReservationService {

    ReservationDto findById(UUID id);

    Page<ReservationDto> findAll(Pageable pageable);

    ReservationDto create(ReservationDto dto);

    ReservationDto update(UUID id, ReservationDto dto);

    void delete(UUID id);
}
