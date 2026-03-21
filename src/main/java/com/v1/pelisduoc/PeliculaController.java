package com.v1.pelisduoc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController() {
        peliculas.add(new Pelicula(1L, "Avengers: Doomsday", 2026, "Anthony Russo y Joe Russo", "Superheroes", "Los heroes se enfrentan a Doctor Doom."));
        peliculas.add(new Pelicula(2L, "The Mandalorian and Grogu", 2026, "Jon Favreau", "Ciencia ficcion", "Mando y Grogu vuelven a una nueva aventura."));
        peliculas.add(new Pelicula(3L, "Toy Story 5", 2026, "Andrew Stanton", "Animacion", "Woody y Buzz regresan en una nueva historia."));
        peliculas.add(new Pelicula(4L, "Shrek 5", 2026, "Walt Dohrn", "Animacion", "Shrek vuelve con una nueva mision."));
        peliculas.add(new Pelicula(5L, "Super Mario World", 2026, "Aaron Horvath y Michael Jelenic", "Animacion", "Mario explora nuevos mundos con sus amigos."));
    }

    @GetMapping
    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    @GetMapping("/{id}")
    public Pelicula getPeliculaPorId(@PathVariable Long id) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId().equals(id)) {
                return pelicula;
            }
        }
        return null;
    }
}