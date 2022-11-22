package br.com.pucminas.hospital.controller;

import br.com.pucminas.hospital.model.dto.AccountCredentialsDTO;
import br.com.pucminas.hospital.model.dto.TokenDTO;
import br.com.pucminas.hospital.model.dto.UserDTO;
import br.com.pucminas.hospital.model.request.CreateUser;
import br.com.pucminas.hospital.services.AuthService;
import br.com.pucminas.hospital.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping(value = "/signin")
    public ResponseEntity<TokenDTO> signin(@RequestBody AccountCredentialsDTO accountCredentialsDTO) {
        var response = authService.signIn(accountCredentialsDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<UserDTO> create(@RequestBody CreateUser userDTO) {
        var response = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Transactional
    @PatchMapping(value = "/change-password/{recoveryToken}")
    public ResponseEntity changePassword(@PathVariable("recoveryToken") String recoveryToken, @RequestHeader("password") String password) {
        userService.changePasswordByToken(recoveryToken, password);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/inactivate-user/{username}")
    public ResponseEntity inactivateUserByUsername(@PathVariable("username") String username) {
        userService.inactivateUser(username);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity<TokenDTO> refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String authorization) {
        var response = authService.refreshToken(username, authorization);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
