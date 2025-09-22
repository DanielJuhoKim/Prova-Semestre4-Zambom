package com.example.insper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {
    @InjectMocks
    private CursoService cursoService;

    @Mock
    private CursoRepository cursoRepository;

    @Test
    public void test_listAllShouldReturnListEmpty() {

        Mockito.when(cursoRepository.findAll()).thenReturn(new ArrayList<>());

        List<Curso> resp = cursoService.getCursos();

        Assertions.assertNotNull(resp);
        Assertions.assertTrue(resp.isEmpty());
    }

    @Test
    public void test_listAllShouldReturnListWithTwoElements() {

        Curso curso = new Curso();
        curso.setId(1);
        curso.setDescricao("Curso teste");
        curso.setTitulo("Curso de testamento");
        curso.setInstrutor("Zamboom");
        curso.setCargaHoraria(30);
        curso.setDataCadastro(LocalDate.now());

        Mockito.when(cursoRepository.findAll()).thenReturn(List.of(curso));

        List<Curso> resp = cursoService.getCursos();

        Assertions.assertNotNull(resp);
        Assertions.assertFalse(resp.isEmpty());
        Assertions.assertEquals(1, resp.size());
        Assertions.assertEquals("Curso teste", resp.get(0).getDescricao());
    }

    @Test
    public void test_listAllShouldReturnOneElement() {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setDescricao("Curso teste");
        curso.setTitulo("Curso de testamento");
        curso.setInstrutor("Zamboom");
        curso.setCargaHoraria(30);
        curso.setDataCadastro(LocalDate.now());

        Mockito.when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));

        Curso resultado = cursoService.getUmCurso(1);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(resultado, curso);
    }
}
