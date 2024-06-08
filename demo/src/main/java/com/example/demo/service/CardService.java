package com.example.demo.service;

import com.example.demo.model.Card;

import java.util.List;
import java.util.UUID;


public interface CardService {
    List<Card> getAllCards();
    void saveCard(Card card);
    List<Card> getCardsByStudentId(UUID studentId);
    void delete(Long id);
}
