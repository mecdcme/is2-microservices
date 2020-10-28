package it.istat.is2.logger.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.istat.is2.logger.domain.LogEntity;
import it.istat.is2.logger.domain.WorkSessionEntity;

public interface LogRepository extends JpaRepository<LogEntity, Integer> {
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
	
	@Transactional
	@Modifying
	@Query("delete from LogEntity lg where lg.user = :userId")
	int deleteByUser(@Param("userId") Long userId);

	List<LogEntity> findByUser(Long userID);
}
