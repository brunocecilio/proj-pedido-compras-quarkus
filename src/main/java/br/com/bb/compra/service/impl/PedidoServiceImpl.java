package br.com.bb.compra.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.com.bb.compra.exception.EstoqueInsuficienteException;
import br.com.bb.compra.exception.OperacaoInvalidaException;
import br.com.bb.compra.model.PedidoRequestDto;
import br.com.bb.compra.model.PedidoResponseDto;
import br.com.bb.compra.model.entity.ClienteEntity;
import br.com.bb.compra.model.entity.ItemPedidoEntity;
import br.com.bb.compra.model.entity.PedidoEntity;
import br.com.bb.compra.model.entity.ProdutoEntity;
import br.com.bb.compra.repository.ClienteRepository;
import br.com.bb.compra.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class PedidoServiceImpl implements PedidoService {

    private final JsonWebToken accessToken;
    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public PedidoResponseDto realizarPedido(PedidoRequestDto pedidoDto) {
        final String email = accessToken.getSubject();
        final ClienteEntity entity = clienteRepository.findByEmail(email);

        final PedidoEntity pedidoEntity = criarPedido(entity, pedidoDto);

        pedidoEntity.persist();

        processaEstoque(pedidoEntity);

        log.info("O usuario {} iniciou pedido {}", email, pedidoDto);
        return new PedidoResponseDto(pedidoEntity.id);
    }

    private void processaEstoque(PedidoEntity pedidoEntity) {
        pedidoEntity.getItens().forEach(item -> {
            final ProdutoEntity produto = item.getProduto();

            if (item.getQuantidade() <= 0) {
                throw new OperacaoInvalidaException(
                        "Quantidade de itens deve ser maior que zero para o item de ID: " + produto.getId());
            }

            if (produto.getEstoque() < item.getQuantidade()) {
                log.warn("Estoque do produto {} insuficiente para concluir o pedido", produto.getNome());
                throw new EstoqueInsuficienteException("Estoque insuficiente do produto de ID: " + produto.getId());
            }
            final int novoEstoque = produto.getEstoque() - item.getQuantidade();
            produto.setEstoque(novoEstoque);
            produto.persist();
        });
    }

    private PedidoEntity criarPedido(ClienteEntity cliente, PedidoRequestDto pedidoDto) {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setCliente(cliente);
        final Set<ItemPedidoEntity> pedidoEntities = pedidoDto.getItens().stream()
                .map(dto -> {
                    final ProdutoEntity produto = ProdutoEntity.findById(dto.getProdutoId());
                    ItemPedidoEntity itemPedido = new ItemPedidoEntity();
                    itemPedido.setProduto(produto);
                    itemPedido.setDesconto(produto.getDesconto());
                    itemPedido.setPreco(produto.getPreco());
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    return itemPedido;
                }).collect(Collectors.toCollection(HashSet::new));
        pedido.setItens(pedidoEntities);
        return pedido;
    }

}
