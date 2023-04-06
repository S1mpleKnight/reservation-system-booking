package by.zelezinsky.reservationsystembooking.service.offer.establishment;

import by.zelezinsky.reservationsystembooking.dto.offer.establishment.EstablishmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EstablishmentService {

    EstablishmentDto create(EstablishmentDto dto);

    EstablishmentDto update(UUID id, EstablishmentDto dto);

    EstablishmentDto findById(UUID id);

    Page<EstablishmentDto> findAll(Pageable pageable);

    void delete(UUID id);
}
