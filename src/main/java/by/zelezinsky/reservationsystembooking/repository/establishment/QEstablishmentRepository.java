package by.zelezinsky.reservationsystembooking.repository.establishment;

import by.zelezinsky.reservationsystembooking.dto.filter.EstablishmentFilter;
import by.zelezinsky.reservationsystembooking.entity.offer.Establishment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QEstablishmentRepository {

    Page<Establishment> findAll(Pageable pageable, EstablishmentFilter filter);
}
