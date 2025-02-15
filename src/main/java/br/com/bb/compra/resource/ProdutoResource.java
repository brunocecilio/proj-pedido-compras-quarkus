package br.com.bb.compra.resource;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.com.bb.compra.model.Produto;
import br.com.bb.compra.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Path("/produtos")
@RequiredArgsConstructor
@Slf4j
public class ProdutoResource {

    @Inject
    @Named("produtoServiceImpl")
    ProdutoService produtoService;

    @GET
    @Path("/{id}")
    public Response getId(@PathParam("id") Long id) {
        return Response.ok(produtoService.buscaPorId(id)).build();
    }

    @POST
    @RolesAllowed("ADMIN")
    public Response salvar(
            @HeaderParam("Authorization") String token,
            @Valid Produto produto) {
        return Response.ok(produtoService.salvar(produto)).build();
    }

    @GET
    public Response listar(@QueryParam("filtro") @DefaultValue("") String filtro,
                           @QueryParam("page") @DefaultValue("0") Integer page,
                           @QueryParam("size") @DefaultValue("20") Integer size) {
        log.info("Recebendo filtro {}", filtro);
        return Response.ok(produtoService.listar(filtro, page, size)).build();
    }

    @GET
    @Path("/unpaged")
    public Response listarTodos() {
        return Response.ok(produtoService.listar()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        produtoService.deletar(id);
        return Response.noContent().build();
    }

}
