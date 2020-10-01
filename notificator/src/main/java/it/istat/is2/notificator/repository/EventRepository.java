package it.istat.is2.notificator.repository;

import it.istat.is2.notificator.domain.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    @Query("select e from EventEntity e where e.emailSent = 0")
    List<EventEntity> findUnsentEmail();
}
