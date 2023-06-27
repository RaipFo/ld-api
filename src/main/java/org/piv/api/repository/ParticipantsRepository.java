package org.piv.api.repository;

import org.piv.api.dao.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantsRepository extends JpaRepository<Participant, Long> {
}
