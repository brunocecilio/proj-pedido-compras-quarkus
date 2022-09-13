package br.com.bb.compra.restclient.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProdutoImportDto {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal discountPercentage;
    private Integer stock;
    private String brand;
    private String thumbnail;

}
