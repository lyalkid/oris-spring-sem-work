package com.example.demo.controller;



import com.example.demo.model.Card;
import com.example.demo.model.Student;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.CardService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class FlashcardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    // Обработка главной страницы
    @GetMapping("/cards")
    public String viewHomePage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Student> student =  studentService.findByUsername(userDetails.getUsername());
        List<Card> listCards = cardService.getCardsByStudentId(student.get().getId());
        model.addAttribute("listCards", listCards);

        return "my-cards"; // Имя шаблона HTML без расширения
    }

    @GetMapping("/cards/new")
    public String createClubForm(Model model) {
        Card card= new Card();
        model.addAttribute("card", card);
        return "card-new";
    }


    // Обработка добавления новой карточки
    @PostMapping("/cards/new")
public String createCard(@ModelAttribute("card") Card card, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Student student = studentRepository.findByUsername(userDetails.getUsername()).get();
        card.setStudent(student);
        cardService.saveCard(card);
        return "redirect:/cards";
    }

    @GetMapping("/cards/delete")
    public String editForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Student student = studentRepository.findByUsername(userDetails.getUsername()).get();
        List<Card> cards = cardService.getCardsByStudentId(student.getId());
        List<Long> ids = cards.stream().map(Card::getId).toList();
        model.addAttribute("user", student);
        model.addAttribute("ids", ids);
        return "delete";
    }

    @GetMapping("/cards/{id}/delete")
    public String deleteCard(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Long k = id;
        cardService.delete(k);
        return "redirect:/cards/delete";
    }


//    @ResponseBody
//    public String addFlashcard(@RequestParam String question, @RequestParam String answer) {
//        if (question.isEmpty() || answer.isEmpty()) {
//            return "Input fields cannot be empty!";
//        }
//        Card newCard = new Card();
//        newCard.setQuestion(question);
//        newCard.setAnswer(answer);
//        cardService.saveCard(newCard);
//        return "Card added successfully!";
//    }

//    // Получение всех карточек
//    @GetMapping("/cards")
//    @ResponseBody
//    public List<Card> getAllCards() {
//        return cardService.getAllCards();
//    }
}
