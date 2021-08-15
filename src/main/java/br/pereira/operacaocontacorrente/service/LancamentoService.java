package br.pereira.operacaocontacorrente.service;

import br.pereira.operacaocontacorrente.api.dto.CedulaOutputDto;
import br.pereira.operacaocontacorrente.api.dto.LancamentoDTO;
import br.pereira.operacaocontacorrente.api.dto.LancamentoInputDto;
import br.pereira.operacaocontacorrente.api.exception.SaqueException;
import br.pereira.operacaocontacorrente.converter.LancamentoConverter;
import br.pereira.operacaocontacorrente.entity.Lancamento;
import br.pereira.operacaocontacorrente.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    @Autowired()
    private LancamentoConverter converter;

    private static final int[] CEDULAS = {100, 50, 20, 10};
    private static final String MSG_VALOR_NAO_PERMITE_SAQUE = "Valor não permite saque.";
    private static final String MSG_ERRO_EFETUAR_LANCAMENTO = "Erro ao efetivar lançamento.";
    private static final String MSG_ERRO_BUSCAR_LANCAMENTO = "Erro ao buscar lançamentos.";

    public List<CedulaOutputDto> sacar(LancamentoInputDto dto, Integer conta ) throws SaqueException {
        try {
            HashMap<Integer, Integer> totalCedulas = buscarValorEmCedulas(dto.getValor());
            List<CedulaOutputDto> cedulasParaSacar = converter.toCedulaOutputDTO(totalCedulas);
            efetivar(converter.toEntity(dto, conta));
            return cedulasParaSacar;
        } catch (Exception e) {
            throw new SaqueException(e.getMessage());
        }
    }

    public HashMap<Integer, Integer> buscarValorEmCedulas(Integer valor) throws SaqueException {
        HashMap<Integer, Integer> totalCedulas = new HashMap<>();
        Integer quantidadeCedulasParaSacar;
        Integer valorParaSacar = valor;

        while (valorParaSacar != 0) {
            for (int i = 0; i < CEDULAS.length; i++){
                if (valorParaSacar >= CEDULAS[i]) {
                    quantidadeCedulasParaSacar = valorParaSacar / CEDULAS[i];
                    valorParaSacar = valorParaSacar % CEDULAS[i];
                    totalCedulas.put(CEDULAS[i], quantidadeCedulasParaSacar);
                }
            }
            if (valorParaSacar > 0 && valorParaSacar < buscarMenorCedula()){
                throw new SaqueException(MSG_VALOR_NAO_PERMITE_SAQUE);
            }
        }
        return totalCedulas;
    }

    public static int buscarMenorCedula(){
        int temp;
        for (int i = 0; i < CEDULAS.length; i++) {
            for (int j = i + 1; j < CEDULAS.length; j++) {
                if (CEDULAS[i] > CEDULAS[j]) {
                    temp = CEDULAS[i];
                    CEDULAS[i] = CEDULAS[j];
                    CEDULAS[j] = temp;
                }
            }
        }
        return CEDULAS[0];
    }

    public void efetivar(Lancamento entity) throws SaqueException {
        try {
            repository.save(entity);
        } catch (Exception e) {
            throw new SaqueException(MSG_ERRO_EFETUAR_LANCAMENTO);
        }
    }

    public List<LancamentoDTO> buscar(Integer conta) throws SaqueException {
        List<LancamentoDTO> lancamentos;
        try {
            lancamentos =  converter.toListaLancamentoDTO(repository.findByConta(conta));
        } catch (Exception e) {
            throw new SaqueException(MSG_ERRO_BUSCAR_LANCAMENTO);
        }
        return lancamentos;
    }

}