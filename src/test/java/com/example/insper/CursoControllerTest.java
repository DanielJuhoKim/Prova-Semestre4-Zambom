package com.example.insper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CursoControllerTest {
    @InjectMocks
    CursoController cursoController;

    @Mock
    CursoService cursoService;

    public MockMvc mockMvc;

    @BeforeEach
    public void setup() { // Vai configurar o mockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(cursoController).build();
    }

    @Test
    public void test_listAllShouldReturnOneStock() throws Exception {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setDescricao("Curso teste");
        curso.setTitulo("Curso de testamento");
        curso.setInstrutor("Zamboom");
        curso.setCargaHoraria(30);
        curso.setDataCadastro(LocalDate.now());

        Mockito.when(cursoService.getCursos()).thenReturn(List.of(curso));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/curso")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].descricao").value("Curso teste"));
    }

    @Test
    public void test_listAllShouldReturnNotFound() throws Exception {
    mockMvc.perform(
                    MockMvcRequestBuilders.get("/curso")
            )
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void test_deleteShouldBeOk() throws Exception {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setDescricao("Curso teste");
        curso.setTitulo("Curso de testamento");
        curso.setInstrutor("Zamboom");
        curso.setCargaHoraria(30);
        curso.setDataCadastro(LocalDate.now());

        Mockito.when(cursoService.getUmCurso(1)).thenReturn(curso);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/curso/1")
            )
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test_deleteShouldBeNotFound() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/curso/1")
        )
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void test_postShouldCreateCurso() throws Exception {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setDescricao("Curso teste");
        curso.setTitulo("Curso de testamento");
        curso.setInstrutor("Zamboom");
        curso.setCargaHoraria(30);
        curso.setDataCadastro(LocalDate.now());

        Mockito.doNothing().when(cursoService).registrarCurso(Mockito.any(Curso.class));

        // JSON que será enviado no POST
        String cursoJson = """
        {
            "id": 1,
            "descricao": "Curso teste",
            "titulo": "Curso de testamento",
            "instrutor": "Zamboom",
            "cargaHoraria": 30,
            "dataCadastro": "2025-09-22"
        }
    """;

        mockMvc.perform(MockMvcRequestBuilders.post("/curso")
        .contentType("application/json")
        .content(cursoJson))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.descricao").value("Curso teste"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Curso de testamento"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.instrutor").value("Zamboom"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.cargaHoraria").value(30));
    }
}
