package by.zelezinsky.reservationsystembooking.service.offer.establishment;

import by.zelezinsky.reservationsystembooking.dto.offer.establishment.EstablishmentDto;
import by.zelezinsky.reservationsystembooking.dto.offer.establishment.EstablishmentDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.Establishment;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.offer.EstablishmentRepository;
import by.zelezinsky.reservationsystembooking.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstablishmentServiceImpl implements EstablishmentService {

    private final EstablishmentDtoMapper establishmentDtoMapper;
    private final EstablishmentRepository establishmentRepository;
    private final UserRepository userRepository;

    @Override
    public EstablishmentDto create(EstablishmentDto dto) {
        User user = findUser(dto.getContactId());
        Establishment entity = establishmentDtoMapper.toEntity(dto);
        entity.setContactId(user.getId());
        return establishmentDtoMapper.toDto(establishmentRepository.save(entity));
    }

    @Override
    public EstablishmentDto update(UUID id, EstablishmentDto dto) {
        Establishment establishment = findEstablishment(id);
        User user = findUser(dto.getContactId());
        establishment = establishmentDtoMapper.toEntity(establishment, dto);
        establishment.setContactId(user.getId());
        return establishmentDtoMapper.toDto(establishmentRepository.save(establishment));
    }

    @Override
    public EstablishmentDto findById(UUID id) {
        return establishmentDtoMapper.toDto(findEstablishment(id));
    }

    @Override
    public Page<EstablishmentDto> findAll(Pageable pageable) {
        return establishmentRepository.findAll(pageable).map(establishmentDtoMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        Establishment establishment = findEstablishment(id);
        establishmentRepository.delete(establishment);
    }

    private Establishment findEstablishment(UUID id) {
        return establishmentRepository.findById(id).orElseThrow(() -> new NotFoundException("User", id.toString()));
    }

    private User findUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", id.toString()));
    }
}
