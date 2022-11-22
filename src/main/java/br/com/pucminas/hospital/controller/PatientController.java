package br.com.pucminas.hospital.controller;


import br.com.pucminas.hospital.model.dto.PatientDTO;
import br.com.pucminas.hospital.model.enums.SurgeryPatientEnum;
import br.com.pucminas.hospital.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping(value = "/find/{register}")
    public ResponseEntity<PatientDTO> find(@PathVariable(value="register") String register) {
        var response = service.findPatientByRegister(register);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<PatientDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "12") int limit) {
        var response = service.findAllPatient(page, limit);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/create-patient")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO,
                                                    @RequestHeader("Authorization") String authorization) {
        var response = service.createPatient(patientDTO, authorization);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(value = "/update-patient")
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO,
                                                    @RequestHeader("Authorization") String authorization) {
        var response = service.updatePatient(patientDTO, authorization);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/get-surgery-types")
    public ResponseEntity<List<SurgeryPatientEnum>> findSurgeryTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(SurgeryPatientEnum.values()));
    }
}