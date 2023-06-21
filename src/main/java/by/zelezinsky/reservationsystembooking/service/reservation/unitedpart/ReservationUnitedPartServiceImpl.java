package by.zelezinsky.reservationsystembooking.service.reservation.unitedpart;

import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunitedpart.ReservationUnitedPartDto;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunitedpart.ReservationUnitedPartDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOfferStatus;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitedPart;
import by.zelezinsky.reservationsystembooking.exception.BadRequestException;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.ReservationUnitRepository;
import by.zelezinsky.reservationsystembooking.repository.offer.ReservationOfferRepository;
import by.zelezinsky.reservationsystembooking.repository.ReservationUnitedPartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReservationUnitedPartServiceImpl implements ReservationUnitedPartService {

    private final ReservationUnitedPartRepository repository;
    private final ReservationUnitedPartDtoMapper mapper;
    private final ReservationOfferRepository reservationOfferRepository;
    private final ReservationUnitRepository reservationUnitRepository;

    @Override
    public ReservationUnitedPartDto findById(UUID id, UUID uuid) {
        ReservationOffer offer = findOffer(id);
        ReservationUnitedPart unitedPart = findUnitedPart(offer, uuid);
        return mapper.toDto(unitedPart);
    }

    @Override
    public Page<ReservationUnitedPartDto> findAll(UUID id, Pageable pageable) {
        if (Objects.isNull(pageable)) {
            pageable = Pageable.unpaged();
        }
        ReservationOffer offer = findOffer(id);
        return repository.findAllByOffer(offer, pageable).map(mapper::toDto);
    }

    @Override
    public ReservationUnitedPartDto create(UUID id, ReservationUnitedPartDto dto) {
        ReservationOffer offer = findOffer(id);
        if (!offer.getOfferStatus().equals(ReservationOfferStatus.NOT_OPEN)) {
            throw new BadRequestException("Can not create united parts in opened offer");
        }
        if (repository.existsByName(dto.getName())) {
            throw new BadRequestException("Reservation united part with that name already exists");
        }
        ReservationUnitedPart entity = mapper.toEntity(dto);
        entity.setOfferId(offer.getId());
        if (Objects.nonNull(dto.getParentId())) {
            ReservationUnitedPart parent = findUnitedPart(offer, dto.getParentId());
            entity.setParentId(parent.getParentId());
            entity.setHasParent(Boolean.TRUE);
        } else {
            entity.setHasParent(Boolean.FALSE);
        }
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(UUID id, UUID uuid) {
        ReservationOffer offer = findOffer(id);
        if (!offer.getOfferStatus().equals(ReservationOfferStatus.NOT_OPEN)) {
            throw new BadRequestException("Can not create united parts in opened offer");
        }
        ReservationUnitedPart unitedPart = findUnitedPart(offer, uuid);
        if (reservationUnitRepository.existsByReservationUnitedPart(unitedPart)) {
            throw new BadRequestException("There are some reservation units in this united part");
        }
        repository.delete(unitedPart);
    }

    private ReservationUnitedPart findUnitedPart(ReservationOffer offer, UUID id) {
        return repository.findByOfferAndId(offer, id)
                .orElseThrow(() -> new NotFoundException("United part", id.toString()));
    }

    private ReservationOffer findOffer(UUID id) {
        return reservationOfferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation offer", id.toString()));
    }
}
