package by.zelezinsky.reservationsystembooking.entity.offer;

import by.zelezinsky.reservationsystembooking.entity.address.City;
import by.zelezinsky.reservationsystembooking.entity.address.Country;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "establishments")
@ToString
public class Establishment {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User contact;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "has_city", nullable = false)
    private Boolean hasCity;

    @Column(name = "street")
    private String street;

    @Column(name = "has_street", nullable = false)
    private String hasStreet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "has_country", nullable = false)
    private Boolean hasCountry;

    @Column(name = "building")
    private String building;

    @Column(name = "has_building", nullable = false)
    private String hasBuilding;

    @Column(name = "apartment")
    private String apartment;

    @Column(name = "has_apartment", nullable = false)
    private Boolean hasApartment;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "establishment")
    private List<ReservationOffer> offers;
}
