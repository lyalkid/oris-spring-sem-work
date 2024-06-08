package com.example.demo.model;

//import javax.persistence.*;
import jakarta.persistence.*;

import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="roles")
public class Role {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String name;
}
