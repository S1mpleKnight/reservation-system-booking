package by.zelezinsky.reservationsystembooking.service.offer.info;

import by.zelezinsky.reservationsystembooking.dto.offer.info.AdditionalOfferInfoDto;
import by.zelezinsky.reservationsystembooking.dto.offer.info.AdditionalOfferInfoDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.AdditionalOfferInfo;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.offer.AdditionalOfferInfoRepository;
import by.zelezinsky.reservationsystembooking.repository.offer.ReservationOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdditionalOfferInfoServiceImpl implements AdditionalOfferInfoService {

    private final AdditionalOfferInfoDtoMapper dtoMapper;
    private final AdditionalOfferInfoRepository infoRepository;
    private final ReservationOfferRepository reservationOfferRepository;

    @Override
    public AdditionalOfferInfoDto create(AdditionalOfferInfoDto dto) {
        ReservationOffer offer = findOffer(dto.getOfferId());
        AdditionalOfferInfo entity = dtoMapper.toEntity(dto);
        entity.setOfferId(offer.getId());
        return dtoMapper.toDto(infoRepository.save(entity));
    }

    @Override
    public AdditionalOfferInfoDto update(UUID id, AdditionalOfferInfoDto dto) {
        AdditionalOfferInfo info = findInfo(id);
        ReservationOffer offer = findOffer(dto.getOfferId());
        info = dtoMapper.toEntity(info, dto);
        info.setOfferId(offer.getId());
        return dtoMapper.toDto(infoRepository.save(info));
    }

    @Override
    public AdditionalOfferInfoDto findById(UUID id) {
        return dtoMapper.toDto(findInfo(id));
    }

    @Override
    public Page<AdditionalOfferInfoDto> findAll(Pageable pageable) {
        return infoRepository.findAll(pageable).map(dtoMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        AdditionalOfferInfo info = findInfo(id);
        infoRepository.delete(info);
    }

    private ReservationOffer findOffer(UUID id) {
        return reservationOfferRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation offer", id.toString()));
    }

    private AdditionalOfferInfo findInfo(UUID id) {
        return infoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Additional info", id.toString()));
    }
}
