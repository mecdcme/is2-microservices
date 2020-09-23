package it.istat.is2.workflowmonitor.repository;

import it.istat.is2.workflowmonitor.domain.WorkflowStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkFlowStatusRepository extends JpaRepository<WorkflowStatusEntity, Long> {
}
