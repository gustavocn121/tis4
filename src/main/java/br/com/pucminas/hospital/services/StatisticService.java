package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.model.dto.ParamsStatisticDTO;
import br.com.pucminas.hospital.model.dto.StatisticDTO;
import br.com.pucminas.hospital.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    @Autowired
    private AssessmentRepository repository;


    public StatisticDTO getStatisticByAssessment(ParamsStatisticDTO paramsStatisticDTO, int page, int limit) {

        StatisticDTO statisticDTO = new StatisticDTO();

        var pageable = PageRequest.of(page, limit);
        var pagePatient = repository.getStatisticByAssessment(paramsStatisticDTO.getInitialDate(), paramsStatisticDTO.getFinalDate());

        //var a = pagePatient.stream().filter(assessment -> {}).count();
        return null;
    }


}
