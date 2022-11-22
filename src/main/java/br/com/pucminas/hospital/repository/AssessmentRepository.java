package br.com.pucminas.hospital.repository;

import br.com.pucminas.hospital.model.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    @Query("SELECT a FROM Assessment a WHERE a.callDay = CURRENT_DATE")
    List<Assessment> findAllDailyAssessment();

    @Query("SELECT a FROM Assessment a WHERE a.patient.register = :register")
    Optional<List<Assessment>> findAssessmentByRegister(@Param("register") String register);

    @Query("SELECT a FROM Assessment a WHERE a.callDay BETWEEN :startDate AND :finalDate")
    List<Assessment> getStatisticByAssessment(@Param("startDate") LocalDate startDate,
                                              @Param("finalDate") LocalDate finalDate);

    @Query(value="select status ,COUNT(*) as qtd from assessment where call_day between  to_date(:startDate ) AND to_date(:endDate) group by status", nativeQuery = true)
    List<Assessment> getStatusStatistic(@Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);

    @Query(value="select call_day, cancel_reason, COUNT(cancel_reason) as qtd from assessment where call_day between  to_date(:startDate, 'YYYY-MM-DD') AND to_date(:endDate, 'YYYY-MM-DD') and cancel_reason is not null group by call_day, cancel_reason", nativeQuery = true)
    List<Assessment> getCancelMotivationStatistic(@Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);

    @Query(value = "select patient_id, count(*) as nao_atendeu_3vzs from assessment where status = 'Enviado' AND patient_id in (select patient_id FROM assessment where assessment_number = 'TERCEIRA' AND call_day between  to_date(:startDate) AND to_date(:endDate) ) group by patient_id having count(*) = 3", nativeQuery = true)
    List<Assessment> getNoResponseStatistic(@Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);

}