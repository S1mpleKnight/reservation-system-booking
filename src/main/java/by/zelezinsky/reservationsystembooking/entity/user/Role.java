package by.zelezinsky.reservationsystembooking.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Table(name = "roles")
@Entity
@Data
public class Role {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<Permission> permissions;
}
