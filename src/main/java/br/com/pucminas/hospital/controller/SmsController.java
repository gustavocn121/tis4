package br.com.pucminas.hospital.controller;


import br.com.pucminas.hospital.model.dto.SmsRequest;
import br.com.pucminas.hospital.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SmsController {

    @Autowired
    private SmsService service;

    @PostMapping(value = "/sms")
    public ResponseEntity sendSms(@RequestBody SmsRequest smsRequest) {
        service.sendSms(smsRequest);
        return ResponseEntity.ok().build();
    }
}