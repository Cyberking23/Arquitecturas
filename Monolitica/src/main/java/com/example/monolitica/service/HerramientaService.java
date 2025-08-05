package com.example.monolitica.service;

import com.example.monolitica.model.Herramienta;
import com.example.monolitica.repository.HerramientaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HerramientaService {
    private final HerramientaRepository herramientaRepository;

    public HerramientaService(HerramientaRepository herramientaRepository) {
        this.herramientaRepository = herramientaRepository;
    }
    public List<Herramienta> listar(){
        return herramientaRepository.findAll();
    }
    public Optional<Herramienta> buscarPorId(Long id){
        return herramientaRepository.findById(id);
    }
    public Optional<Herramienta> actualizar(Long id, Herramienta herramientaActualizada) {
        return herramientaRepository.findById(id).map(h -> {
            h.setNombre(herramientaActualizada.getNombre());
            h.setTipo(herramientaActualizada.getTipo());
            h.setMarca(herramientaActualizada.getMarca());
            return herramientaRepository.save(h);
        });
    }

    public Herramienta guardar(Herramienta herramienta) {
        return herramientaRepository.save(herramienta);
    }
    public void eliminar(Long id) {
        herramientaRepository.deleteById(id);
    }
}
