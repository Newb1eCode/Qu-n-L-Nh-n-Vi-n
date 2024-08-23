package com.example.demotestxuong.model.repository;

import com.example.demotestxuong.model.entities.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MajorRepo extends JpaRepository<Major, UUID> {
}
