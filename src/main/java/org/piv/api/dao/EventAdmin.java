package org.piv.api.dao;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(exclude = {"id", "user"})
@Builder
@Entity
@Table(name = "event_admins")
public class EventAdmin {
    @Id
    private Long id;
    private String org_name;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Event> events;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
