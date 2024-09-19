package com.app.booking_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class Audit {

    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;

    @Column(name="created_by")
    private String createdBy;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;

    @Column(name="updated_by")
    private String updatedBy;

}
