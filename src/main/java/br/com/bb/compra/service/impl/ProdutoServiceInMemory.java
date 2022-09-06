package br.com.bb.compra.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.bb.compra.model.Produto;
import br.com.bb.compra.model.ProdutoListDto;
import br.com.bb.compra.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
@Named("produtoServiceInMemory")
public class ProdutoServiceInMemory implements ProdutoService {

    private static Map<Long, Produto> MAP = new HashMap<>();
    private static AtomicLong GERADOR_ID = new AtomicLong();

    @Override
    public Produto salvar(Produto novoProduto) {
        novoProduto.setId(GERADOR_ID.incrementAndGet());
        MAP.put(novoProduto.getId(), novoProduto);
        log.trace("Produto salvo: {}", novoProduto);
        return novoProduto;
    }

    @Override
    public List<Produto> salvarLista(List<Produto> novoProduto) {
        novoProduto.forEach(produto -> produto.setId(GERADOR_ID.incrementAndGet()));
        novoProduto.forEach(produto -> MAP.put(produto.getId(), produto));
        log.trace("Produtos salvos: {}", novoProduto);
        return novoProduto;
    }

    @Override
    public Produto buscaPorId(Long id) {
        return MAP.get(id);
    }

    @Override
    public boolean isEmpty() {
        return MAP.isEmpty();
    }

    @Override
    public ProdutoListDto listar(String filtro, Integer page, Integer size) {
        List<Produto> produtos = MAP.values().stream()
                .sorted((p1, p2) -> p1.getId().compareTo(p2.getId()))
                .skip(page * size)
                .limit(size)
                .collect(Collectors.toList());
        int pageCount = (int) Math.ceil((double) MAP.size() / size.doubleValue());

        return ProdutoListDto.builder()
                .list(produtos)
                .size(produtos.size())
                .page(page)
                .total(pageCount)
                .build();
    }

    @Override
    public List<Produto> listar() {
        return MAP.values().stream()
                .sorted((p1, p2) -> p1.getId().compareTo(p2.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void deletar(Long id) {
        MAP.remove(id);
        log.trace("Produto deletado: {}", MAP.get(id));
    }

}
