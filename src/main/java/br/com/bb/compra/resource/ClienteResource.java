package br.com.bb.compra.resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;

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


    //@QueryParam -> www.bb.com.br?nome=Onofre&idade=12
    //@PathParam -> www.bb.com.br/clientes/{id}/valor
    @GET
    @Path(value ="cpfByEmail")
    public List<String> cpfByEmail(@QueryParam("email") String email) {
        return clienteService.cpfByEmail(email);
    }

    @GET
    @Path(value ="email/{minhaVariavel}/outracoisa")
    public List<String> cpfByEmailParam(@PathParam("minhaVariavel") String email) {
        return clienteService.cpfByEmail(email);
    }

    @GET
    @Path(value ="mapCliente")
    public List<Cliente> mapCliente(@QueryParam("nome") String nome) {
        return clienteService.mapCliente(nome);
    }

    @POST
    @Operation(description = "Salvar um cliente")
    public Response criarCliente(@Valid Cliente cliente) {
        clienteService.salvarCliente(cliente);
        return Response.ok(cliente)
                .build();
    }

}