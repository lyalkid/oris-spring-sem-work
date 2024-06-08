package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentService {

   Optional<Student> findByUsername(String username);
}
