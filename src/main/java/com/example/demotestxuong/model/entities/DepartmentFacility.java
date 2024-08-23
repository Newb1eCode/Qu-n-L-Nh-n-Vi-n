package com.example.demotestxuong.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "department_facility")
@Entity
public class DepartmentFacility {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "status")
    private Byte status;

    @Column(name = "created_date")
    private Long createdDate;

    @Column(name = "last_modified_date")
    private Long lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "id_department")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "id_facility")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "id_staff")
    private Staff staff;
}
