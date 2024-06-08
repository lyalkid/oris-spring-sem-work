package com.example.demo.repository;

import com.example.demo.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findByStudentId(UUID studentId);
    Optional<Card> findById(Long id);
    void deleteById(Long id);
}
