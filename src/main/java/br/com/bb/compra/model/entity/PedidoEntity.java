package br.com.bb.compra.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PedidoEntity extends PanacheEntity {

    @ManyToOne
    private ClienteEntity cliente;

    // mappedBy refere-se ao nome da variavel pedido na classe ItemPedidoEntity
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Set<ItemPedidoEntity> itens;
}
