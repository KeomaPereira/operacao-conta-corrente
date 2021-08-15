package utils;

import br.pereira.operacaocontacorrente.api.dto.LancamentoDTO;
import br.pereira.operacaocontacorrente.api.dto.LancamentoInputDto;
import br.pereira.operacaocontacorrente.entity.Lancamento;

import java.util.*;

public class MockUtils {

    public static final Integer CEDULA_DEZ = 10;
    public static final Integer VALOR_SAQUE_PERMITIDO = 30;
    public static final Integer CONTA = 110;
    public static final Integer VALOR = 160;
    public static final Integer CEDULA_VINTE = 20;
    public static final Integer QUANTIDADE = 1;
    public static final String MSG_ERRO_EFETUAR_LANCAMENTO = "Erro ao efetivar lançamento.";

    public static HashMap<Integer, Integer> gerarCedulas(){
        HashMap<Integer, Integer> cedulas = new HashMap<>();
        cedulas.put(CEDULA_DEZ, QUANTIDADE);
        cedulas.put(CEDULA_VINTE, QUANTIDADE);
        return cedulas;
    }

    public static LancamentoInputDto gerarLancamentoInputDto() {
        LancamentoInputDto dto = new LancamentoInputDto();
        dto.setValor(VALOR_SAQUE_PERMITIDO);
        return dto;
    }

    public static List<LancamentoDTO> gerarListaLancamentoDTO() {
        LancamentoDTO dto = new LancamentoDTO();
        List<LancamentoDTO> lista = new ArrayList<>();
        dto.setValor(VALOR);
        dto.setConta(CONTA);
        lista.add(dto);
        return lista;
    }

    public static Lancamento gerarLancamento() {
        Lancamento ent = new Lancamento();
        ent.setValor(VALOR);
        ent.setConta(CONTA);
        return ent;
    }

}