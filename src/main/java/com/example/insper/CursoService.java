package com.example.insper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getCursos() {
        return cursoRepository.findAll();
    }

    public Curso getUmCurso(Integer id) {
        return cursoRepository.findById(id).get();
    }

    public void registrarCurso(Curso curso) {
        cursoRepository.save(curso);
    }

    public void deletarCurso(Integer id) {
        cursoRepository.deleteById(id);
    }

}
