package by.zelezinsky.reservationsystembooking.service.offer.reservationoffer;

import by.zelezinsky.reservationsystembooking.dto.filter.ReservationOfferFilter;
import by.zelezinsky.reservationsystembooking.dto.offer.info.AdditionalOfferInfoDtoMapper;
import by.zelezinsky.reservationsystembooking.dto.offer.reservationoffer.ReservationOfferDto;
import by.zelezinsky.reservationsystembooking.dto.offer.reservationoffer.ReservationOfferDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.*;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.*;
import by.zelezinsky.reservationsystembooking.repository.UserRepository;
import by.zelezinsky.reservationsystembooking.repository.establishment.EstablishmentRepository;
import by.zelezinsky.reservationsystembooking.repository.event.EventRepository;
import by.zelezinsky.reservationsystembooking.repository.offer.ReservationOfferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    private final AdditionalOfferInfoDtoMapper additionalOfferInfoDtoMapper;
    private final AdditionalOfferInfoRepository additionalOfferInfoRepository;

    @Override
    @Transactional
    public ReservationOfferDto create(ReservationOfferDto dto) {
        ReservationOffer entity = reservationOfferDtoMapper.toEntity(dto);
        User user = findUser(dto.getContactId());
        entity.setContactId(user.getId());
        setOfferEvent(dto, entity);
        setOfferEstablishment(dto, entity);
        setOfferAdditionalInfo(dto, entity);
        setOfferCategories(dto, entity);
        entity.setOfferStatus(ReservationOfferStatus.NOT_OPEN);
        return reservationOfferDtoMapper.toDto(reservationOfferRepository.save(entity));
    }

    @Override
    @Transactional
    public ReservationOfferDto update(UUID id, ReservationOfferDto dto) {
        ReservationOffer offer = findOffer(id);
        User user = findUser(dto.getContactId());
        offer = reservationOfferDtoMapper.toEntity(offer, dto);
        setOfferEvent(dto, offer);
        setOfferEstablishment(dto, offer);
        updateAdditionalInfo(dto, offer);
        updateOfferCategories(dto, offer);
        offer.setContactId(user.getId());
        return reservationOfferDtoMapper.toDto(reservationOfferRepository.save(offer));
    }

    @Override
    public ReservationOfferDto findById(UUID id) {
        return reservationOfferDtoMapper.toDto(findOffer(id));
    }

    @Override
    public Page<ReservationOfferDto> findAll(Pageable pageable, ReservationOfferFilter filter) {
        if (Objects.isNull(pageable)) {
            pageable = Pageable.unpaged();
        }
        return reservationOfferRepository.findAll(pageable, filter).map(reservationOfferDtoMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        ReservationOffer offer = findOffer(id);
        reservationOfferRepository.delete(offer);
        //todo: correct with adding reservation
    }

    @Override
    public ReservationOfferDto changeStatusAndContact(UUID id, UUID contactId, ReservationOfferStatus status) {
        ReservationOffer offer = findOffer(id);
        User user = findUser(contactId);
        offer.setContactId(user.getId());
        offer.setOfferStatus(status);
        return reservationOfferDtoMapper.toDto(reservationOfferRepository.save(offer));
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

    private void setOfferEvent(ReservationOfferDto dto, ReservationOffer entity) {
        if (Boolean.TRUE.equals(dto.getHasEvent())) {
            Event event = findEvent(dto.getEventId());
            entity.setEventId(event.getId());
            entity.setHasEvent(Boolean.TRUE);
        } else {
            entity.setHasEvent(Boolean.FALSE);
        }
    }

    private void setOfferEstablishment(ReservationOfferDto dto, ReservationOffer entity) {
        if (Boolean.TRUE.equals(dto.getHasEstablishment())) {
            Establishment establishment = findEstablishment(dto.getEstablishmentId());
            entity.setEstablishmentId(establishment.getId());
            entity.setHasEstablishment(Boolean.TRUE);
        } else {
            entity.setHasEstablishment(Boolean.FALSE);
        }
    }

    private void setOfferAdditionalInfo(ReservationOfferDto dto, ReservationOffer entity) {
        if (Boolean.TRUE.equals(dto.getHasAdditionalInfo())) {
            AdditionalOfferInfo info = additionalOfferInfoDtoMapper.toEntity(dto.getAdditionalOfferInfo());
            entity.setInfo(info);
            info.setOfferId(entity.getId());
            entity.setHasAdditionalInfo(Boolean.TRUE);
        } else {
            entity.setHasAdditionalInfo(Boolean.FALSE);
        }
    }

    private void setOfferCategories(ReservationOfferDto dto, ReservationOffer entity) {
        List<OfferCategory> allCategories = offerCategoryRepository.findAllById(dto.getCategoryIds());
        checkOfferCategories(dto, allCategories);
        entity.setCategories(allCategories);
    }

    private void updateOfferCategories(ReservationOfferDto dto, ReservationOffer entity) {
        List<OfferCategory> allCategories = offerCategoryRepository.findAllById(dto.getCategoryIds());
        checkOfferCategories(dto, allCategories);
        entity.getCategories().clear();
        entity.getCategories().addAll(allCategories);
    }

    private void updateAdditionalInfo(ReservationOfferDto dto, ReservationOffer offer) {
        if (Boolean.TRUE.equals(dto.getHasAdditionalInfo())) {
            AdditionalOfferInfo info
                    = additionalOfferInfoDtoMapper.toEntity(offer.getInfo(), dto.getAdditionalOfferInfo());
            offer.setInfo(info);
            info.setOfferId(offer.getId());
            offer.setHasAdditionalInfo(Boolean.TRUE);
        } else {
            offer.setHasAdditionalInfo(Boolean.FALSE);
            additionalOfferInfoRepository.delete(offer.getInfo());
        }
    }
}
