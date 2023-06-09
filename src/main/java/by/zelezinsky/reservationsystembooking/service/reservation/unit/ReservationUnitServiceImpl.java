package by.zelezinsky.reservationsystembooking.service.reservation.unit;

import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit.ReservationUnitDto;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit.ReservationUnitDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOfferStatus;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnit;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitType;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitedPart;
import by.zelezinsky.reservationsystembooking.exception.BadRequestException;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.offer.ReservationOfferRepository;
import by.zelezinsky.reservationsystembooking.repository.ReservationUnitRepository;
import by.zelezinsky.reservationsystembooking.repository.ReservationUnitTypeRepository;
import by.zelezinsky.reservationsystembooking.repository.ReservationUnitedPartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationUnitServiceImpl implements ReservationUnitService {

    private final ReservationUnitRepository reservationUnitRepository;
    private final ReservationUnitDtoMapper reservationUnitDtoMapper;
    private final ReservationOfferRepository reservationOfferRepository;
    private final ReservationUnitedPartRepository reservationUnitedPartRepository;
    private final ReservationUnitTypeRepository reservationUnitTypeRepository;

    @Override
    public ReservationUnitDto findById(UUID id, UUID uuid) {
        ReservationOffer offer = findOffer(id);
        return reservationUnitDtoMapper.toDto(findUnit(offer, uuid));
    }

    @Override
    public Page<ReservationUnitDto> findAll(UUID id, Pageable pageable) {
        if (Objects.isNull(pageable)) {
            pageable = Pageable.unpaged();
        }
        ReservationOffer offer = findOffer(id);
        return reservationUnitRepository.findAllByOffer(offer, pageable).map(reservationUnitDtoMapper::toDto);
    }

    @Override
    public ReservationUnitDto create(UUID id, ReservationUnitDto dto) {
        ReservationOffer offer = findOffer(id);
        if (!offer.getOfferStatus().equals(ReservationOfferStatus.NOT_OPEN)) {
            throw new BadRequestException("Can not create units in opened offer");
        }
        ReservationUnit entity = reservationUnitDtoMapper.toEntity(dto);
        setUnitedPart(dto, offer, entity);
        setUnitType(dto, offer, entity);
        entity.setOffer(offer);
        return reservationUnitDtoMapper.toDto(reservationUnitRepository.save(entity));
    }

    @Override
    public ReservationUnitDto update(UUID id, UUID uuid, ReservationUnitDto dto) {
        ReservationOffer offer = findOffer(id);
        if (!offer.getOfferStatus().equals(ReservationOfferStatus.NOT_OPEN)) {
            throw new BadRequestException("Can not update units in opened offer");
        }
        ReservationUnit unit = findUnit(offer, uuid);
        unit = reservationUnitDtoMapper.toEntity(unit, dto);
        setUnitedPart(dto, offer, unit);
        return reservationUnitDtoMapper.toDto(reservationUnitRepository.save(unit));
    }

    @Override
    public void delete(UUID id, UUID uuid) {
        ReservationOffer offer = findOffer(id);
        ReservationUnit unit = findUnit(offer, uuid);
        reservationUnitRepository.delete(unit);
        //todo: correct after reservation
    }

    private ReservationOffer findOffer(UUID id) {
        return reservationOfferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation offer", id.toString()));
    }

    private ReservationUnit findUnit(ReservationOffer offer, UUID uuid) {
        return reservationUnitRepository.findByOfferAndId(offer, uuid)
                .orElseThrow(() -> new NotFoundException("Reservation unit", uuid.toString()));
    }

    private ReservationUnitedPart findUnitedPart(ReservationOffer offer, UUID id) {
        return reservationUnitedPartRepository.findByOfferAndId(offer, id)
                .orElseThrow(() -> new NotFoundException("Reservation united part", id.toString()));
    }

    private ReservationUnitType findType(ReservationOffer offer, UUID id) {
        return reservationUnitTypeRepository.findByOfferAndId(offer, id)
                .orElseThrow(() -> new NotFoundException("Unit type", id.toString()));
    }

    private void setUnitedPart(ReservationUnitDto dto, ReservationOffer offer, ReservationUnit entity) {
        if (Boolean.TRUE.equals(dto.getHasUnitedPart())) {
            ReservationUnitedPart unitedPart = findUnitedPart(offer, dto.getReservationUnitedPartId());
            entity.setReservationUnitedPart(unitedPart);
            entity.setHasUnitedPart(Boolean.TRUE);
        } else {
            entity.setHasUnitedPart(Boolean.FALSE);
        }
    }

    private void setUnitType(ReservationUnitDto dto, ReservationOffer offer, ReservationUnit entity) {
        if (Boolean.TRUE.equals(dto.getHasReservationUnitType())) {
            ReservationUnitType type = findType(offer, dto.getReservationUnitTypeId());
            entity.setReservationUnitType(type);
            entity.setHasReservationUnitType(Boolean.TRUE);
        } else {
            entity.setHasReservationUnitType(Boolean.FALSE);
        }
    }
}
