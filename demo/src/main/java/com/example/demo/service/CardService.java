package com.example.demo.service;

import com.example.demo.model.Card;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CardService {
    List<Card> getAllCards();
    void saveCard(Card card);
    Optional<Card> getCardById(Long id);
    Optional<Card> getCardById(Integer id);
    List<Card> getCardsByStudentId(UUID studentId);
    void delete(Long id);
}
