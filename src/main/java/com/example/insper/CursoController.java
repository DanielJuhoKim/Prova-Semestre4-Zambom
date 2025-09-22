package com.example.insper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CursoController {
    @Autowired
    CursoService cursoService;

    @GetMapping("/curso")
    public List<Curso> getCurso() {
        if (cursoService.getCursos().size() > 0){
            return cursoService.getCursos();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há cursos registrados");
    }

    @PostMapping("/curso")
    public Curso registrarCurso(@RequestBody Curso curso) {
        cursoService.registrarCurso(curso);

        return curso;
    }

    @DeleteMapping("/curso/{id}")
    public Curso deletarCurso(@PathVariable Integer id) {
        Curso cursoDeletar = cursoService.getUmCurso(id);
        if (cursoDeletar != null){
            cursoService.deletarCurso(id);
            return cursoDeletar;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso " + id + " não encontrado");
    }
}
