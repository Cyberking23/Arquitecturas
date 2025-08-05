package com.example.monolitica.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Herramienta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Importante para MySQL
    private Long id;

    private String nombre;
    private String tipo;
    private String marca;
}
