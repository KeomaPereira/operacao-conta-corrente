package br.pereira.operacaocontacorrente.api.dto;

public class CedulaOutDto {

    private Integer cedula;

    private Integer quantidade;

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}