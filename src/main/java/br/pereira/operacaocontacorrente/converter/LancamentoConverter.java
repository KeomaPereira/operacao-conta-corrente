package br.pereira.operacaocontacorrente.converter;

import br.pereira.operacaocontacorrente.api.dto.CedulaOutputDto;
import br.pereira.operacaocontacorrente.api.dto.LancamentoOutputDTO;
import br.pereira.operacaocontacorrente.api.dto.LancamentoInputDto;
import br.pereira.operacaocontacorrente.entity.Lancamento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class LancamentoConverter {

    private static final String SAQUE = "Saque";

    public Lancamento toEntity (LancamentoInputDto dto, Integer conta) {
        Lancamento entity = new Lancamento();
        entity.setConta(conta);
        entity.setTipo(SAQUE);
        entity.setValor(dto.getValor());
        entity.setData(LocalDateTime.now());
        return entity;
    }

    public List<CedulaOutputDto> toListaCedulaOutputDTO(HashMap<Integer, Integer> cedulas) {
        List<CedulaOutputDto> listaCedulas = new ArrayList<>();

        for(Map.Entry<Integer, Integer> cedula : cedulas.entrySet()) {
            CedulaOutputDto dto = new CedulaOutputDto();
            dto.setCedula(cedula.getKey());
            dto.setQuantidade(cedula.getValue());
            listaCedulas.add(dto);
        }
        listaCedulas.sort(Comparator.comparing(CedulaOutputDto:: getCedula));
        return listaCedulas;
    }

    public LancamentoOutputDTO toLancamentoDTO (Lancamento entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, LancamentoOutputDTO.class);
    }

    public List<LancamentoOutputDTO> toListaLancamentoDTO (List<Lancamento> entities) {
        List<LancamentoOutputDTO> listaLancamentosDTO = new ArrayList<>();
        entities.stream().forEach(ent -> {
            listaLancamentosDTO.add(toLancamentoDTO(ent));
        });
        return listaLancamentosDTO;
    }

}