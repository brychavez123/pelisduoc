package com.v1.pelisduoc;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaRepository peliculaRepository;

    public PeliculaController(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    @GetMapping
    public List<Pelicula> getPeliculas() {
        return peliculaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> getPeliculaPorId(@PathVariable Long id) {
        return peliculaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pelicula> crearPelicula(@RequestBody Pelicula pelicula) {
        pelicula.setId(null);
        Pelicula creada = peliculaRepository.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        return peliculaRepository.findById(id)
                .map(existente -> {
                    existente.setTitulo(pelicula.getTitulo());
                    existente.setAnio(pelicula.getAnio());
                    existente.setDirector(pelicula.getDirector());
                    existente.setGenero(pelicula.getGenero());
                    existente.setSinopsis(pelicula.getSinopsis());
                    Pelicula actualizada = peliculaRepository.save(existente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Long id) {
        if (!peliculaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        peliculaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}