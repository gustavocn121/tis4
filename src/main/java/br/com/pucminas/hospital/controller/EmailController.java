package br.com.pucminas.hospital.controller;

import br.com.pucminas.hospital.model.dto.EmailDto;
import br.com.pucminas.hospital.model.dto.UserDTO;
import br.com.pucminas.hospital.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/change-password/")
    public ResponseEntity<EmailDto> generateRecoveryTokenEmail(@RequestBody UserDTO userDTO) {
        var emailDto = emailService.sendPasswordRecoveryEmail(userDTO.getUserName());
        return new ResponseEntity(emailDto, HttpStatus.OK);
    }

}
