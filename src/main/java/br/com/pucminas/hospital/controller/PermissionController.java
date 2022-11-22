package br.com.pucminas.hospital.controller;

import br.com.pucminas.hospital.model.dto.PermissionDTO;
import br.com.pucminas.hospital.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService service;

    @PostMapping(value = "/create/{description}")
    public ResponseEntity<PermissionDTO> createPermission(@RequestParam String description) {
        var response = service.createPermission(description);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<PermissionDTO>> findAll() {
        var response = service.findAllPermission();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(value = "/delete/{description}")
    public ResponseEntity<Void> deletarUsuarioPorId(@RequestParam String description) {
        service.deleteByDescription(description);
        return ResponseEntity.ok().build();
    }
}
