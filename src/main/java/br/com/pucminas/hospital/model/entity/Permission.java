package br.com.pucminas.hospital.model.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "permission")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "permission_generator")
    @SequenceGenerator(name = "permission_generator", sequenceName = "permission_sequence")
    private Long idPermission;

    @Column(name = "description", unique = true)
    private String description;

    @Override
    public String getAuthority() {
        return this.description;
    }

}
