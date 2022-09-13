package br.com.bb.compra.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.bb.compra.exception.ErrorMessage;
import br.com.bb.compra.exception.EstoqueInsuficienteException;

@Provider
public class EstoqueInsuficienteExceptionHandler implements ExceptionMapper<EstoqueInsuficienteException> {

    @Override
    public Response toResponse(EstoqueInsuficienteException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorMessage(e.getMessage())).build();
    }

}
