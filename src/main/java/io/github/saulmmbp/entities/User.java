package io.github.saulmmbp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    private String id;
    private String email;

    private AuditMetadata auditMetadata;

}
