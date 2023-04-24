package by.zelezinsky.reservationsystembooking.service.reservation.reservationunitedpart;

import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunitedpart.ReservationUnitedPartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReservationUnitedPartService {
    
    ReservationUnitedPartDto findById(UUID id, UUID uuid);
    
    Page<ReservationUnitedPartDto> findAll(UUID id, Pageable pageable);
    
    ReservationUnitedPartDto create(UUID id, ReservationUnitedPartDto dto);

    void delete(UUID id, UUID uuid);
}
