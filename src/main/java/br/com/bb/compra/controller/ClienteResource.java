package br.com.bb.compra.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bb.compra.model.Cliente;
import br.com.bb.compra.service.ClienteService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/clientes")
public class ClienteResource {

    @Inject
    @Named("clienteServiceImpl")
    ClienteService clienteService;

    @GET
    public List<Cliente> clientes() {
        return clienteService.getClientes();
    }

    @POST
    public Response criarCliente(@Valid Cliente cliente) {
        clienteService.salvarCliente(cliente);
        return Response.ok(cliente)
                .build();
    }

}