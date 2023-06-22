package by.zelezinsky.reservationsystembooking.service.reservation;

import by.zelezinsky.reservationsystembooking.dto.reservation.ReservationDto;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit.ReservationInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ReservationService {

    ReservationDto findById(UUID id);

    Page<ReservationDto> findAll(Pageable pageable);

    List<ReservationInfoDto> findAllPersonal(String username);

    ReservationDto create(ReservationDto dto);

    ReservationDto update(UUID id, ReservationDto dto);

    void delete(UUID id);
}
