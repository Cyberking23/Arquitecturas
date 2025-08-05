package com.example.monolitica.controller;

import com.example.monolitica.model.Herramienta;
import com.example.monolitica.service.HerramientaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/herramientas")
public class HerramientaController {
    private final HerramientaService herramientaService;

    public HerramientaController(HerramientaService herramientaService) {
        this.herramientaService = herramientaService;
    }

    @GetMapping
    public List<Herramienta> listar() {
        return herramientaService.listar();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Herramienta> obtenerPorId(@PathVariable Long id) {
        return herramientaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Herramienta guardar(@RequestBody Herramienta herramienta) {
        return herramientaService.guardar(herramienta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        herramientaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
