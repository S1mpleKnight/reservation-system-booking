package by.zelezinsky.reservationsystembooking.service.offer.info;

import by.zelezinsky.reservationsystembooking.dto.offer.info.AdditionalOfferInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AdditionalOfferInfoService {

    AdditionalOfferInfoDto create(AdditionalOfferInfoDto dto);

    AdditionalOfferInfoDto update(UUID id, AdditionalOfferInfoDto dto);

    AdditionalOfferInfoDto findById(UUID id);

    Page<AdditionalOfferInfoDto> findAll(Pageable pageable);

    void delete(UUID id);
}
