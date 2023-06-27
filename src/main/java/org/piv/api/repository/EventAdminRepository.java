package org.piv.api.repository;

import org.piv.api.dao.EventAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventAdminRepository extends JpaRepository<EventAdmin, Long> {
    Optional<EventAdmin> findById(Long Id);
}
