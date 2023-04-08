package by.zelezinsky.reservationsystembooking.service.reservation.reservationunittype;

import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunittype.ReservationUnitTypeDto;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunittype.ReservationUnitTypeDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitType;
import by.zelezinsky.reservationsystembooking.exception.BadRequestException;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.offer.ReservationOfferRepository;
import by.zelezinsky.reservationsystembooking.repository.reservation.ReservationUnitTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationUnitTypeServiceImpl implements ReservationUnitTypeService {

    private final ReservationUnitTypeRepository unitTypeRepository;
    private final ReservationUnitTypeDtoMapper dtoMapper;
    private final ReservationOfferRepository reservationOfferRepository;

    @Override
    public ReservationUnitTypeDto create(UUID offerId, ReservationUnitTypeDto dto) {
        ReservationOffer offer = findOffer(offerId);
        if (unitTypeRepository.existsByNameAndOfferId(dto.getName(), offerId)) {
            throw new BadRequestException("Unit type with that name already exists");
        }
        ReservationUnitType entity = dtoMapper.toEntity(dto);
        entity.setOfferId(offer.getId());
        return dtoMapper.toDto(unitTypeRepository.save(entity));
    }

    @Override
    public ReservationUnitTypeDto update(UUID offerId, UUID id, ReservationUnitTypeDto dto) {
        ReservationOffer offer = findOffer(offerId);
        ReservationUnitType type = findType(id);
        if (unitTypeRepository.existsByNameAndOfferId(dto.getName(), id)) {
            throw new BadRequestException("Unit type with that name already exists");
        }
        type = dtoMapper.toEntity(type, dto);
        type.setOfferId(offer.getId());
        return dtoMapper.toDto(unitTypeRepository.save(type));
    }

    @Override
    public ReservationUnitTypeDto findById(UUID id) {
        return dtoMapper.toDto(findType(id));
    }

    @Override
    public Page<ReservationUnitTypeDto> findAll(Pageable pageable) {
        return unitTypeRepository.findAll(pageable).map(dtoMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        ReservationUnitType type = findType(id);
        unitTypeRepository.delete(type);
    }

    private ReservationOffer findOffer(UUID id) {
        return reservationOfferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation offer", id.toString()));
    }

    private ReservationUnitType findType(UUID id) {
        return unitTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation unit type", id.toString()));
    }
}
