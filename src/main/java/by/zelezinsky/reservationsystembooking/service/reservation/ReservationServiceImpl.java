package by.zelezinsky.reservationsystembooking.service.reservation;

import by.zelezinsky.reservationsystembooking.dto.reservation.ReservationDto;
import by.zelezinsky.reservationsystembooking.dto.reservation.ReservationDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import by.zelezinsky.reservationsystembooking.entity.reservation.Reservation;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnit;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import by.zelezinsky.reservationsystembooking.exception.BadRequestException;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.ReservationRepository;
import by.zelezinsky.reservationsystembooking.repository.ReservationUnitRepository;
import by.zelezinsky.reservationsystembooking.repository.UserRepository;
import by.zelezinsky.reservationsystembooking.service.mailer.MailerSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private static final String CREATE_RESERVATION_MESSAGE = "You reservation for the %s succeed on %s in %s";
    private static final String DELETE_RESERVATION_MESSAGE = "You reservation for the %s deleted on %s in %s";
    private static final String UPDATE_RESERVATION_MESSAGE = "You reservation for the %s updated on %s in %s";
    private final ReservationRepository reservationRepository;
    private final ReservationDtoMapper reservationDtoMapper;
    private final UserRepository userRepository;
    private final ReservationUnitRepository reservationUnitRepository;
    private final MailerSenderService mailerSenderService;

    @Override
    public ReservationDto findById(UUID id) {
        return reservationDtoMapper.toDto(findReservation(id));
    }

    @Override
    public Page<ReservationDto> findAll(Pageable pageable) {
        if (Objects.isNull(pageable)) {
            pageable = Pageable.unpaged();
        }
        return reservationRepository.findAll(pageable).map(reservationDtoMapper::toDto);
    }

    @Override
    @Transactional
    public ReservationDto create(ReservationDto dto) {
        User user = findUser(dto.getUserId());
        List<ReservationUnit> units = findUnits(dto.getUnitIds());
        Reservation entity = reservationDtoMapper.toEntity(dto);
        entity.setUserId(user.getId());
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        entity.setReservationTime(now.toLocalTime());
        entity.setReservationDate(now.toLocalDate());
        Reservation save = reservationRepository.save(entity);
        units = saveUnits(units, save);
        save.setUnits(units);
        ReservationOffer offer = units.get(0).getOffer();
        mailerSenderService.send(user.getEmail(), offer.getName(),
                createMessageText(save, offer, CREATE_RESERVATION_MESSAGE));
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
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        reservation.setReservationTime(now.toLocalTime());
        reservation.setReservationDate(now.toLocalDate());
        Reservation save = reservationRepository.save(reservation);
        units = saveUnits(units, save);
        ReservationOffer offer = units.get(0).getOffer();
        mailerSenderService.send(user.getEmail(), offer.getName(),
                createMessageText(save, offer, UPDATE_RESERVATION_MESSAGE));
        reservation.setUnits(units);
        return reservationDtoMapper.toDto(save);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Reservation reservation = findReservation(id);
        List<ReservationUnit> units = reservation.getUnits();
        units.forEach(unit -> unit.setReservation(null));
        ReservationOffer offer = units.get(0).getOffer();
        String body = createMessageText(reservation, offer, DELETE_RESERVATION_MESSAGE);
        mailerSenderService.send(reservation.getUser().getEmail(), offer.getName(), body);
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
        Map<ReservationOffer, List<ReservationUnit>> offerUnits = presentUnits
                .stream()
                .collect(Collectors.groupingBy(ReservationUnit::getOffer));
        if (offerUnits.keySet().size() != 1) {
            throw new BadRequestException("Units should belong to the one reservation offer");
        }
        return presentUnits;
    }

    private List<ReservationUnit> saveUnits(List<ReservationUnit> units, Reservation save) {
        units.forEach(unit -> unit.setReservationId(save.getId()));
        units = reservationUnitRepository.saveAll(units);
        return units;
    }

    private String createMessageText(Reservation reservation, ReservationOffer offer, String pattern) {
        LocalTime reservationTime = reservation.getReservationTime();
        LocalDate reservationDate = reservation.getReservationDate();
        String offerName = offer.getName();
        return String.format(pattern, offerName, reservationDate, reservationTime);
    }
}
