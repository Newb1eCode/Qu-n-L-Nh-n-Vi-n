package com.example.demotestxuong.model.repository;

import com.example.demotestxuong.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, UUID> {
}
