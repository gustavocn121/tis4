package br.com.pucminas.hospital.controller;

import br.com.pucminas.hospital.model.dto.UserDTO;
import br.com.pucminas.hospital.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserTokenService userTokenService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<UserDTO>> getAllUserByUsername(@RequestHeader("Authorization") String authorization) {
        var user = userTokenService.findAllUsersByToken(authorization);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping(value = "/find-username-by-token")
    public ResponseEntity<UserDTO> findUserByUsername(@RequestHeader("Authorization") String authorization) {
        var user = userTokenService.findUserByToken(authorization);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping(value = "/update-user")
    public ResponseEntity<UserDTO> atualizarUsuario(@RequestHeader("Authorization") String authorization, @RequestBody UserDTO userDTO) {
        var response = userTokenService.updateUser(authorization, userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}