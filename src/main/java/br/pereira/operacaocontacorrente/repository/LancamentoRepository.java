package br.pereira.operacaocontacorrente.repository;

import br.pereira.operacaocontacorrente.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LancamentoRepository
        extends JpaRepository<Lancamento, Long> {

    List<Lancamento> findByConta(Integer conta);

}