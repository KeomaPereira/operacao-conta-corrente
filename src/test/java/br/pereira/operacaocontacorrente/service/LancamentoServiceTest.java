package br.pereira.operacaocontacorrente.service;

import br.pereira.operacaocontacorrente.api.exception.SaqueException;
import br.pereira.operacaocontacorrente.converter.LancamentoConverter;
import br.pereira.operacaocontacorrente.repository.LancamentoRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import utils.MockUtils;

import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class LancamentoServiceTest {

    @InjectMocks
    private LancamentoService service;

    @Mock
    private LancamentoRepository repository;

    @Mock
    private LancamentoConverter converter;

    @After
    public void reset_mocks() {
        Mockito.reset(service);
    }

    @Test
    void deveBuscarValorEmCedulasComSucesso() throws SaqueException {
        HashMap<Integer, Integer> valorEmCedulasBuscado = service.buscarValorEmCedulas(MockUtils.VALOR_SAQUE_PERMITIDO);
        HashMap<Integer, Integer> valorEmCedulasParaComparar = MockUtils.gerarCedulas();

        Assert.assertEquals(valorEmCedulasParaComparar, valorEmCedulasBuscado);
    }

    @Test
    void deveSacarComSucesso() throws SaqueException {
        service.sacar(MockUtils.gerarLancamentoInputDto(), MockUtils.CONTA);
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void naoDeveSacarComSucesso() {
        try {
            Mockito.when(repository.save(Mockito.any())).thenThrow(Mockito.mock(DataAccessException.class));
            service.sacar(MockUtils.gerarLancamentoInputDto(), MockUtils.CONTA);
        } catch (Exception e) {
            Assert.assertEquals(MockUtils.MSG_ERRO_EFETUAR_LANCAMENTO, e.getMessage());
        }
    }

}