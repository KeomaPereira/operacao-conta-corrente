package br.pereira.operacaocontacorrente.api;

import br.pereira.operacaocontacorrente.api.dto.CedulaOutputDto;
import br.pereira.operacaocontacorrente.api.dto.LancamentoOutputDTO;
import br.pereira.operacaocontacorrente.api.dto.LancamentoInputDto;
import br.pereira.operacaocontacorrente.api.exception.SaqueException;
import br.pereira.operacaocontacorrente.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/conta-corrente/lancamentos")

public class LancamentoRest {

    @Autowired
    private LancamentoService lancamentoService;

    @PostMapping("saques/{conta}")
    public ResponseEntity<List<CedulaOutputDto>> sacar(@PathVariable("conta") Integer conta,
                                       @Valid @RequestBody LancamentoInputDto dto) throws SaqueException {

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoService.sacar(dto, conta));
    }

    @GetMapping("saques/{conta}")
    public ResponseEntity<List<LancamentoOutputDTO>> buscar(@PathVariable("conta") Integer conta) throws SaqueException {

        List<LancamentoOutputDTO> lista =  lancamentoService.buscar(conta);
        return (lista.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(lista);
    }

}