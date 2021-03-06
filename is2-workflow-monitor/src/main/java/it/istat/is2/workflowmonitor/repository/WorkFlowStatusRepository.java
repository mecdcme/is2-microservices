package it.istat.is2.workflowmonitor.repository;

import it.istat.is2.workflowmonitor.domain.WorkflowStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkFlowStatusRepository extends JpaRepository<WorkflowStatusEntity, Long> {

    Optional<WorkflowStatusEntity> findTopByWorkSessionIdAndBusinessFunctionIdOrderByCreationDateDesc(@Param("workSessionId") Long workSessionId, @Param("businessFunctionId") Long businessFunctionId);
    List<WorkflowStatusEntity> findAllByWorkSessionIdOrderByCreationDate(@Param("workSessionId") Long workSessionId);
    List<WorkflowStatusEntity> findAllByWorkSessionIdAndBusinessFunctionIdOrderByCreationDate(@Param("workSessionId") Long workSessionId, @Param("businessFunctionId") Long businessFunctionId);
}
