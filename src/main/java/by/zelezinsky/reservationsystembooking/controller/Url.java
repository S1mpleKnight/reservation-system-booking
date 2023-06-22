package by.zelezinsky.reservationsystembooking.controller;

public interface Url {
    String ID = "{id}";
    String UUID = "{uuid}";

    interface Role {
        String BASE = "roles/";
    }

    interface User {
        String BASE = "users/";

        interface Authentication {
            String BASE = "auth/";
        }

        interface Registration {
            String BASE = "registration/";
        }

        interface Profile {
            String BASE = "profile/";
            String PASSWORD = BASE + "password/";
        }
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
            String BASE = "types/";
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
        String PERSONAL = "personal/";
    }

    interface Country {
        String BASE = "countries/";
    }

    interface City {
        String BASE = "cities/";
    }
}
