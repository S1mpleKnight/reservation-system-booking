package by.zelezinsky.reservationsystembooking.entity.offer;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "event")
@Entity
public class Event {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "event_time")
    private LocalTime time;

    @Column(name = "has_time", nullable = false)
    private Boolean hasTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "establishment_id", nullable = false, insertable = false, updatable = false)
    private Establishment establishment;

    @Column(name = "establishment_id", nullable = false)
    private UUID establishmentId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private List<ReservationOffer> offers;
}
