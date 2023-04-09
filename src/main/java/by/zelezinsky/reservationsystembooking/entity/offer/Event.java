package by.zelezinsky.reservationsystembooking.entity.offer;

import by.zelezinsky.reservationsystembooking.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private List<ReservationOffer> offer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    private User contact;

    @Column(name = "user_id")
    private UUID contactId;
}
