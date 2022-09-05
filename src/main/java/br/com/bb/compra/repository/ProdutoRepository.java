package br.com.bb.compra.repository;

import javax.enterprise.context.ApplicationScoped;

import br.com.bb.compra.model.entity.ProdutoEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepositoryBase<ProdutoEntity, Long> {

    public PanacheQuery<ProdutoEntity> findByNomeOrDescricao(String filtro, Page page) {
        return find("nome like ?1 OR descricao like ?2 ", "%" + filtro + "%", "%" + filtro + "%").page(page);
    }

}
