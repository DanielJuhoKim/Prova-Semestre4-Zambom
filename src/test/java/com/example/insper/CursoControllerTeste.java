package com.example.insper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class CursoControllerTeste {
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
    public void test_deleteShouldBeOk() throws Exception {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setDescricao("Curso teste");
        curso.setTitulo("Curso de testamento");
        curso.setInstrutor("Zamboom");
        curso.setCargaHoraria(30);
        curso.setDataCadastro(LocalDate.now());

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/curso/1")
            )
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
