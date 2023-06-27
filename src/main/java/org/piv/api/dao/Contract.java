package org.piv.api.dao;

import lombok.*;
import org.piv.api.model.enums.Status;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(exclude = "id")
@Builder
@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private EventAdmin eventAdmin;
    @ManyToOne(fetch = FetchType.LAZY)
    private Principal principal;
    @Enumerated(EnumType.STRING)
    private Status status;
}
