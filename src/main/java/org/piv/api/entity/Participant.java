package org.piv.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
