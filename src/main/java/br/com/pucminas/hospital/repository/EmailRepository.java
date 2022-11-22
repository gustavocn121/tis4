package br.com.pucminas.hospital.repository;

import br.com.pucminas.hospital.model.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
