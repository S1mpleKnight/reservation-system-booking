package by.zelezinsky.reservationsystembooking.service.offer.reservationoffer;

import by.zelezinsky.reservationsystembooking.dto.offer.reservationoffer.ReservationOfferDto;
import by.zelezinsky.reservationsystembooking.dto.offer.reservationoffer.ReservationOfferDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.*;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.offer.EstablishmentRepository;
import by.zelezinsky.reservationsystembooking.repository.offer.EventRepository;
import by.zelezinsky.reservationsystembooking.repository.offer.OfferCategoryRepository;
import by.zelezinsky.reservationsystembooking.repository.offer.ReservationOfferRepository;
import by.zelezinsky.reservationsystembooking.repository.reservation.ReservationUnitRepository;
import by.zelezinsky.reservationsystembooking.repository.reservation.ReservationUnitTypeRepository;
import by.zelezinsky.reservationsystembooking.repository.reservation.ReservationUnitedPartRepository;
import by.zelezinsky.reservationsystembooking.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationOfferServiceImpl implements ReservationOfferService {

    private final ReservationOfferRepository reservationOfferRepository;
    private final ReservationOfferDtoMapper reservationOfferDtoMapper;
    private final UserRepository userRepository;
    private final EstablishmentRepository establishmentRepository;
    private final EventRepository eventRepository;
    private final OfferCategoryRepository offerCategoryRepository;
    private final ReservationUnitTypeRepository reservationUnitTypeRepository;
    private final ReservationUnitRepository reservationUnitRepository;
    private final ReservationUnitedPartRepository reservationUnitedPartRepository;

    @Override
    @Transactional
    public ReservationOfferDto create(ReservationOfferDto dto) {
        ReservationOffer entity = reservationOfferDtoMapper.toEntity(dto);
        User user = findUser(dto.getContactId());
        if (Boolean.TRUE.equals(dto.getHasEvent())) {
            Event event = findEvent(dto.getEventId());
            entity.setEventId(event.getId());
        }
        if (Boolean.TRUE.equals(dto.getHasEstablishment())) {
            Establishment establishment = findEstablishment(dto.getEstablishmentId());
            entity.setEstablishmentId(establishment.getId());
        }
        List<OfferCategory> allCategories = offerCategoryRepository.findAllById(dto.getCategoryIds());
        checkOfferCategories(dto, allCategories);
        entity.setCategories(allCategories);
        entity.setContactId(user.getId());
        entity.setHasAdditionalInfo(Boolean.FALSE);
        entity.setHasEvent(Boolean.FALSE);
        entity.setOfferStatus(ReservationOfferStatus.NOT_OPEN);
        return reservationOfferDtoMapper.toDto(reservationOfferRepository.save(entity));
    }

    @Override
    public ReservationOfferDto update(UUID id, ReservationOfferDto dto) {
        return null;
    }

    @Override
    public ReservationOfferDto findById(UUID id) {
        return null;
    }

    @Override
    public Page<ReservationOfferDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public ReservationOfferDto changeStatus(UUID id, UUID contactId, ReservationOfferStatus status) {
        return null;
    }

    private User findUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", id.toString()));
    }

    private ReservationOffer findOffer(UUID id) {
        return reservationOfferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation offer", id.toString()));
    }

    private Establishment findEstablishment(UUID id) {
        return establishmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Establishment", id.toString()));
    }

    private Event findEvent(UUID id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event", id.toString()));
    }

    private void checkOfferCategories(ReservationOfferDto dto, List<OfferCategory> allCategories) {
        if (allCategories.size() < dto.getCategoryIds().size()) {
            List<UUID> presentIds = allCategories.stream().map(OfferCategory::getId).toList();
            dto.getCategoryIds().removeAll(presentIds);
            String missedCategories = dto.getCategoryIds()
                    .stream()
                    .map(UUID::toString)
                    .collect(Collectors.joining(", "));
            throw new NotFoundException("Offer category", missedCategories);
        }
    }
}
