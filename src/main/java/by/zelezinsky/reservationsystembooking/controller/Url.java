package by.zelezinsky.reservationsystembooking.controller;

public interface Url {
    String ID = "{id}";
    String UUID = "{uuid}";

    interface Role {
        String BASE = "roles/";
    }

    interface User {
        String BASE = "users/";
    }

    interface Event {
        String BASE = "events/";
    }

    interface Category {
        String BASE = "categories/";
    }

    interface ReservationOffer {
        String BASE = "offers/";

        interface UnitType {
            String BASE = "type/";
            String FULL = ReservationOffer.BASE + ID + "/" + BASE;
        }

        interface UnitedPart {
            String BASE = "parts/";
            String FULL = ReservationOffer.BASE + ID + "/" + BASE;
        }

        interface Unit {
            String BASE = "units/";
            String FULL = ReservationOffer.BASE + ID + "/" + BASE;
        }
    }

    interface Establishment {
        String BASE = "establishments/";
    }

    interface Reservation {
        String BASE = "reservations/";
    }
}
