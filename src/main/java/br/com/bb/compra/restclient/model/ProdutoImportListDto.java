package br.com.bb.compra.restclient.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProdutoImportListDto {

    private List<ProdutoImportDto> products;

}
