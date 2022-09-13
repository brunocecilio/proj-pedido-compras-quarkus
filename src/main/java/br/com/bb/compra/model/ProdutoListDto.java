package br.com.bb.compra.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProdutoListDto {

    private List<Produto> list;
    private int page;
    private int size;
    private int total;

}
