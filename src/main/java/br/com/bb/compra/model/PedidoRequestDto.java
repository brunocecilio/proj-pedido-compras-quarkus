package br.com.bb.compra.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoRequestDto {

    private List<ItemPedidoDto> itens;
}
