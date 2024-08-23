package com.example.demotestxuong.model.repository;

import com.example.demotestxuong.model.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StaffRepo extends JpaRepository<Staff, UUID> {
}
