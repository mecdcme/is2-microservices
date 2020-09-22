package it.istat.is2.logger.repository;

import it.istat.is2.logger.domain.LogEntity;
import it.istat.is2.logger.domain.WorkSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface LogRepository extends JpaRepository<LogEntity, Integer>
{
    List<LogEntity> findByWorkSessionOrderByIdAsc(WorkSessionEntity idWorkSession);
    List<LogEntity> findByWorkSessionAndTypeOrderByIdAsc(WorkSessionEntity idWorkSession, String type);

    @Transactional
    @Modifying
    @Query("delete from LogEntity lg where lg.workSession = :idWorkSession and lg.type = :type")
    int deleteByWorkSessionAndType(@Param("idWorkSession") Long idWorkSession, @Param("type") String type);

    @Transactional
    @Modifying
    @Query("delete from LogEntity lg where lg.workSession.id = :idWorkSession")
    int deleteByWorkSession(@Param("idWorkSession") Long idWorkSession);
}
