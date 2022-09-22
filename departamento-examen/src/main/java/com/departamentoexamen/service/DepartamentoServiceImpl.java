package com.departamentoexamen.service;

import com.departamentoexamen.clients.ClienteClientRest;
import com.departamentoexamen.models.Cliente;
import com.departamentoexamen.models.entity.Departamento;
import com.departamentoexamen.models.entity.DepartamentoCliente;
import com.departamentoexamen.repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{

    @Autowired
    private DepartamentoRepository repository;

    @Autowired
    private ClienteClientRest client;

    @Override
    @Transactional(readOnly = true)
    public List<Departamento> listar() {
        return (List<Departamento>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Departamento> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Departamento guardar(Departamento departamento) {
        return repository.save(departamento);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Departamento> porIdConCliente(Long id) {
        Optional<Departamento> o=repository.findById(id);
        if (o.isPresent()){
            Departamento departamento=o.get();
            if (!departamento.getDepartamentoClientes().isEmpty()){
                List<Long> ids=departamento.getDepartamentoClientes().stream().map(cu->cu.getClienteId())
                        .collect(Collectors.toList());

                List<Cliente> clientes=client.obtenerClientesPorDepartamento(ids);
                departamento.setClientes(clientes);
            }
            return Optional.of(departamento);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void eliminarDepartamentoClientePorId(Long id) {
         repository.eliminarDepartamentoClientePorId(id);
    }

    @Override
    @Transactional
    public Optional<Cliente> asignarCliente(Cliente cliente, Long departamentoId) {
        Optional<Departamento> o = repository.findById(departamentoId);
        if (o.isPresent()){
            Cliente clienteExamen=client.detalle(cliente.getId());

            Departamento departamento=o.get();
            DepartamentoCliente departamentoCliente=new DepartamentoCliente();
            departamentoCliente.setClienteId(clienteExamen.getId());

            departamento.addDepartamentoCliente(departamentoCliente);
            repository.save(departamento);

            return Optional.of(clienteExamen);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Cliente> crearCliente(Cliente cliente, Long departamentoId) {
        Optional<Departamento> o = repository.findById(departamentoId);
        if (o.isPresent()){
            Cliente clienteNuevo=client.crear(cliente);

            Departamento departamento=o.get();
            DepartamentoCliente departamentoCliente=new DepartamentoCliente();
            departamentoCliente.setClienteId(clienteNuevo.getId());

            departamento.addDepartamentoCliente(departamentoCliente);
            repository.save(departamento);

            return Optional.of(clienteNuevo);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Cliente> eliminarCliente(Cliente cliente, Long departamentoId) {
        Optional<Departamento> o = repository.findById(departamentoId);
        if (o.isPresent()){
            Cliente clienteExamen=client.detalle(cliente.getId());

            Departamento departamento=o.get();
            DepartamentoCliente departamentoCliente=new DepartamentoCliente();
            departamentoCliente.setClienteId(clienteExamen.getId());

            departamento.addDepartamentoCliente(departamentoCliente);
            repository.save(departamento);

            return Optional.of(clienteExamen);
        }
        return Optional.empty();
    }
}
