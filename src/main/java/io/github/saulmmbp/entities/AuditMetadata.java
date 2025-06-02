package io.github.saulmmbp.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class AuditMetadata {

    @CreatedDate
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdDate;

    @LastModifiedDate
    @UpdateTimestamp
    private LocalDate LastModifiedDate;

}
