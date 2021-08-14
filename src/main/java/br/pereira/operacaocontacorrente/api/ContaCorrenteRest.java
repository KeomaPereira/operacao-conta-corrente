package br.pereira.operacaocontacorrente.api;

import br.pereira.operacaocontacorrente.api.dto.CedulaOutDto;
import br.pereira.operacaocontacorrente.api.dto.LancamentoInputDto;
import br.pereira.operacaocontacorrente.api.exception.SaqueException;
import br.pereira.operacaocontacorrente.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/conta-corrente")

class ContaCorrenteRest {

    @Autowired
    private LancamentoService lancamentoService;

    @PostMapping("/sacar/{conta}")
    public List<CedulaOutDto> sacar(@PathVariable("conta") Integer conta,
                                    @Valid @RequestBody LancamentoInputDto dto) throws SaqueException {

        return lancamentoService.sacar(dto, conta);
    }

}