package br.com.bb.compra.service;

import java.util.List;

import br.com.bb.compra.model.Cliente;

public interface ClienteService {
    void salvarCliente(Cliente cliente);
    List<Cliente> getClientes();
    Cliente findByEmail(String email);
    List<String> cpfByEmail(String email);
    List<Cliente> mapCliente(String nome);
}
