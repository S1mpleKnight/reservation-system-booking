package by.zelezinsky.reservationsystembooking.controller;

public interface Url {
    String ID = "{id}";

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
    }

    interface Establishment {
        String BASE = "establishments/";
    }
}
