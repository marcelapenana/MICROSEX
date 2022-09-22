package com.clienteexamen.service;

import com.clienteexamen.modelsentity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> listar();

    Optional<Cliente> porId(Long id);

    Cliente guardar(Cliente cliente);

    void eliminar(Long id);

    List<Cliente>listarPorIds(Iterable<Long>ids);

    Optional<Cliente>porEmail(String email);

    boolean existePorEmail(String email);

}
