package by.zelezinsky.reservationsystembooking.entity.offer;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Table(name = "events")
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

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "has_end_date", nullable = false)
    private Boolean hasEndDate;

    @Column(name = "event_time")
    private LocalTime time;

    @Column(name = "has_time", nullable = false)
    private Boolean hasTime;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "event")
    private ReservationOffer offer;
}
