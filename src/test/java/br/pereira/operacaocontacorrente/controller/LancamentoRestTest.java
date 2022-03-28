package br.pereira.operacaocontacorrente.controller;

import br.pereira.operacaocontacorrente.api.LancamentoRest;
import br.pereira.operacaocontacorrente.api.dto.LancamentoOutputDTO;
import br.pereira.operacaocontacorrente.service.LancamentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LancamentoRestTest {

    MockMvc mockMvc;

    @InjectMocks
    private LancamentoRest lancamentoRest;

    @Mock
    private LancamentoService service;

    @Test
    void deveBuscarSaquesDaContaComSucesso() throws Exception {
        LancamentoOutputDTO dto = getLancamentoOutputDTO();
        mockMvc = MockMvcBuilders.standaloneSetup(lancamentoRest).build();
        Mockito.when(service.buscar(Mockito.any())).thenReturn(List.of(dto));

        mockMvc.perform(get("/conta-corrente/lancamentos/saques/56"))
                .andExpect(status().isOk());
    }

    private LancamentoOutputDTO getLancamentoOutputDTO() {
        LancamentoOutputDTO dto = new LancamentoOutputDTO();
        dto.setConta(56);
        return dto;
    }

}