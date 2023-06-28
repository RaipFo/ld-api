package org.piv.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.piv.api.model.enums.Status;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
