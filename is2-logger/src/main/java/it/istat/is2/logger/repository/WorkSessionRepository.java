package it.istat.is2.logger.repository;

import it.istat.is2.logger.domain.WorkSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkSessionRepository extends JpaRepository<WorkSessionEntity, Long> {
}
