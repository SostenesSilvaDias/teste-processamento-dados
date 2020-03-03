package br.com.gft.testeprocessamentodados.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CalculoRetorno {

    private int loja;
    private int quantidadeProdutos;
    private BigDecimal financeiro;
    private BigDecimal precoMedio;

    public CalculoRetorno(){}

}
