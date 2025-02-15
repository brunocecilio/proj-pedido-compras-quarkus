package br.com.bb.compra.restclient;


import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.bb.compra.restclient.model.ProdutoImportListDto;

@RegisterRestClient(baseUri = "https://dummyjson.com")
public interface ProdutoRestClient {

    @GET
    @Path(value = "/products/search")
    ProdutoImportListDto findAll(
            @HeaderParam("Authorization") String token,
            @QueryParam("q") String categoria);

}
