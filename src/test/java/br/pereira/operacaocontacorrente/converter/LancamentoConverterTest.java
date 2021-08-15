package br.pereira.operacaocontacorrente.converter;

import br.pereira.operacaocontacorrente.api.dto.CedulaOutputDto;
import br.pereira.operacaocontacorrente.api.dto.LancamentoOutputDTO;
import br.pereira.operacaocontacorrente.entity.Lancamento;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.MockUtils;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class LancamentoConverterTest {

    @InjectMocks
    private LancamentoConverter converter;

    @Test
    void deveConverterParaLancamentoComSucesso() {
        Lancamento lancamentoConvertido = converter.toEntity(MockUtils.gerarLancamentoInputDto(), MockUtils.CONTA);
        Assert.assertEquals(MockUtils.CONTA, lancamentoConvertido.getConta());
    }

    @Test
    void deveConverterParaCedulaOutputDTOComSucesso() {
        List<CedulaOutputDto> cedulasConvertidas = converter.toListaCedulaOutputDTO(MockUtils.gerarCedulas());
        Assert.assertEquals(MockUtils.CEDULA_DEZ, cedulasConvertidas.get(0).getCedula());
    }

    @Test
    void deveConverterParaLancamentoDTOComSucesso() {
        LancamentoOutputDTO dtoConvertido = converter.toLancamentoDTO(MockUtils.gerarLancamento());
        Assert.assertEquals(MockUtils.CONTA, dtoConvertido.getConta());
    }

    @Test
    void toListaLancamentoDTO() {
        List<LancamentoOutputDTO> listaLancamentosDtosConvertidos = MockUtils.gerarListaLancamentoDTO();
        Assert.assertEquals(MockUtils.CONTA, listaLancamentosDtosConvertidos.get(0).getConta());
    }
}