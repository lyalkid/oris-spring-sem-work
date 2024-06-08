package com.example.demo.repository;

import com.example.demo.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck, Integer> {
}
