//package com.example.demo.controller;
//
//import com.example.demo.model.Card;
//import com.example.demo.model.Student;
//import com.example.demo.repository.CardRepository;
//import com.example.demo.repository.StudentRepository;
//import com.example.demo.service.CardService;
//import com.example.demo.service.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.Optional;
//
//@Controller
//public class EditCardController {
//    @Autowired
//    private CardService cardService;
//    @Autowired
//    private CardRepository cardRepository;
//
//    @Autowired
//    private StudentService studentService;
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @PostMapping("/cards")
//    public ResponseEntity<String> createOrUpdateFlashcard(@RequestBody Card flashcard, @AuthenticationPrincipal UserDetails userDetails) {
//        try {
//            Student student = studentRepository.findByUsername(userDetails.getUsername()).orElseThrow();
//            flashcard.setStudent(student);
//
//            if (flashcard.getId() != null) {
//                // Проверка существующей карточки
//                Optional<Card> existingCard = cardRepository.getCardById(flashcard.getId());
//                if (existingCard.isPresent()) {
//                    Card cardToUpdate = existingCard.get();
//                    cardToUpdate.setQuestion(flashcard.getQuestion());
//                    cardToUpdate.setAnswer(flashcard.getAnswer());
//                    cardRepository.save(cardToUpdate);
//                } else {
//                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
//                }
//            } else {
//                cardRepository.save(flashcard);
//            }
//
//            return ResponseEntity.ok("Card saved successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save flashcard");
//        }
//    }
//
//}
