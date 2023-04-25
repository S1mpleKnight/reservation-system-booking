package by.zelezinsky.reservationsystembooking.service.reservation;

import by.zelezinsky.reservationsystembooking.dto.reservation.ReservationDto;
import by.zelezinsky.reservationsystembooking.dto.reservation.ReservationDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.reservation.Reservation;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnit;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.reservation.ReservationRepository;
import by.zelezinsky.reservationsystembooking.repository.reservation.ReservationUnitRepository;
import by.zelezinsky.reservationsystembooking.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationDtoMapper reservationDtoMapper;
    private final UserRepository userRepository;
    private final ReservationUnitRepository reservationUnitRepository;

    @Override
    public ReservationDto findById(UUID id) {
        return reservationDtoMapper.toDto(findReservation(id));
    }

    @Override
    public Page<ReservationDto> findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable).map(reservationDtoMapper::toDto);
    }

    @Override
    @Transactional
    public ReservationDto create(ReservationDto dto) {
        User user = findUser(dto.getUserId());
        List<ReservationUnit> units = findUnits(dto.getUnitIds());
        Reservation entity = reservationDtoMapper.toEntity(dto);
        entity.setUserId(user.getId());
        Reservation save = reservationRepository.save(entity);
        units = saveUnits(units, save);
        entity.setUnits(units);
        return reservationDtoMapper.toDto(save);
    }

    @Override
    @Transactional
    public ReservationDto update(UUID id, ReservationDto dto) {
        Reservation reservation = findReservation(id);
        User user = findUser(dto.getUserId());
        List<ReservationUnit> units = findUnits(dto.getUnitIds());
        reservation = reservationDtoMapper.toEntity(reservation, dto);
        reservation.setUserId(user.getId());
        Reservation save = reservationRepository.save(reservation);
        units = saveUnits(units, save);
        reservation.setUnits(units);
        return reservationDtoMapper.toDto(save);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Reservation reservation = findReservation(id);
        List<ReservationUnit> units = reservation.getUnits();
        units.forEach(unit -> {
            unit.setReservation(null);
        });
        reservationUnitRepository.saveAll(units);
        reservationRepository.delete(reservation);
    }

    private User findUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", id.toString()));
    }

    private Reservation findReservation(UUID id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation", id.toString()));
    }

    private List<ReservationUnit> findUnits(List<UUID> unitIds) {
        List<ReservationUnit> presentUnits = reservationUnitRepository.findAllById(unitIds);
        if (presentUnits.size() != unitIds.size()) {
            List<UUID> presentIds = presentUnits.stream().map(ReservationUnit::getId).toList();
            unitIds.removeAll(presentIds);
            List<String> stringIds = unitIds.stream().map(UUID::toString).toList();
            throw new NotFoundException("Reservation unit", stringIds);
        }
        return presentUnits;
    }

    private List<ReservationUnit> saveUnits(List<ReservationUnit> units, Reservation save) {
        units.forEach(unit -> {
            unit.setReservationId(save.getId());
        });
        units = reservationUnitRepository.saveAll(units);
        return units;
    }
}
