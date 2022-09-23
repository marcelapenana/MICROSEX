package com.clienteexamen.service;

import com.clienteexamen.clients.DepartamentoClientRest;
import com.clienteexamen.modelsentity.Cliente;
import com.clienteexamen.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private DepartamentoClientRest client;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return (List<Cliente>) repository.findAll();
    }

    @Override
    @Transactional
    public Optional<Cliente> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Cliente guardar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
       repository.deleteById(id);
       client.eliminarDepartamentoClientePorId(id);
    }

    @Override
    @Transactional
    public List<Cliente> listarPorIds(Iterable<Long> ids) {
        return (List<Cliente>) repository.findAllById(ids);
    }

    @Override
    @Transactional
    public Optional<Cliente> porEmail(String email) {
        return repository.porEmail(email);
    }

    @Override
    @Transactional
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }
}
