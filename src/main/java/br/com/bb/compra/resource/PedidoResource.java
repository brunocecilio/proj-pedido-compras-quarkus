package br.com.bb.compra.resource;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;

import br.com.bb.compra.model.PedidoRequestDto;
import br.com.bb.compra.model.PedidoResponseDto;
import br.com.bb.compra.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Path("/pedidos")
@RequiredArgsConstructor
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    @Operation(description = "Realizar pedido de compras")
    @POST
    @RolesAllowed("CLIENTE")
    public Response realizarPedido(
            @Valid PedidoRequestDto request) {
        final PedidoResponseDto response = pedidoService.realizarPedido(request);
        return Response.ok(response).build();
    }
}
