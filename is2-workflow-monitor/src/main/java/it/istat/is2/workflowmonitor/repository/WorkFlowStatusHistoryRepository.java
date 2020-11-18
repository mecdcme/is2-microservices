package it.istat.is2.workflowmonitor.repository;

import it.istat.is2.workflowmonitor.domain.WorkflowStatusHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkFlowStatusHistoryRepository extends JpaRepository<WorkflowStatusHistoryEntity, Long> {
}
