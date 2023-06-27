package org.piv.api.dao;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(exclude = {"id", "user"})
@Builder
@Entity
@Table(name = "principals")
public class Principal {
    @Id
    private Long id;
    private Long inn;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;
}
