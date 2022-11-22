package br.com.pucminas.hospital.repository;

import br.com.pucminas.hospital.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findByDescription(String description);

    @Transactional
    void deleteByDescription(String description);
}