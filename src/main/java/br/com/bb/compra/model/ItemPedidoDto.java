package br.com.bb.compra.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDto {

    @NotNull
    private Long produtoId;
    @NotNull
    private Integer quantidade;

}
