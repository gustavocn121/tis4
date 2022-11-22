package br.com.pucminas.hospital.model.entity;


import br.com.pucminas.hospital.model.dto.EmailDto;
import br.com.pucminas.hospital.model.enums.StatusEmail;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="email")
@NoArgsConstructor
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail = StatusEmail.PENDING;


    public Email(EmailDto emailDto){
        this.statusEmail = StatusEmail.PENDING;
        this.sendDateEmail = LocalDateTime.now();
        BeanUtils.copyProperties(emailDto, this);
    }
}
