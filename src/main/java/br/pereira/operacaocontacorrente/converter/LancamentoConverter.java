package br.pereira.operacaocontacorrente.converter;

import br.pereira.operacaocontacorrente.api.dto.CedulaOutDto;
import br.pereira.operacaocontacorrente.api.dto.LancamentoInputDto;
import br.pereira.operacaocontacorrente.entity.Lancamento;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LancamentoConverter {

    private static final String SAQUE = "Saque";

    public Lancamento toEntity (LancamentoInputDto dto, Integer conta) {
        Lancamento entity = new Lancamento();
        entity.setConta(conta);
        entity.setTipo(SAQUE);
        entity.setValor(dto.getValor());
        return entity;
    }

    public List<CedulaOutDto> toDTO(HashMap<Integer, Integer> cedulas) {
        List<CedulaOutDto> listaCedulas = new ArrayList<>();

        for(Map.Entry<Integer, Integer> cedula : cedulas.entrySet()) {
            CedulaOutDto dto = new CedulaOutDto();
            dto.setCedula(cedula.getKey());
            dto.setQuantidade(cedula.getValue());
            listaCedulas.add(dto);
        }
        listaCedulas.sort(Comparator.comparing(CedulaOutDto :: getCedula));
        return listaCedulas;

    }
}