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
@Table(name = "participants")
public class Participant {
    @Id
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private Integer age;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "participants")
    private List<Event> events;
}
