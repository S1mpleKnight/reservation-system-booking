package by.zelezinsky.reservationsystembooking.entity.reservation;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    RESERVED,
    FREE,
    NOT_AVAILABLE,
    WAITING
}
