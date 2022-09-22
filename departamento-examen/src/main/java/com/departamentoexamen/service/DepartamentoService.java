package com.departamentoexamen.service;

import com.departamentoexamen.models.Cliente;
import com.departamentoexamen.models.entity.Departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentoService {

    List<Departamento> listar();

    Optional<Departamento> porId(Long id);

    Departamento guardar(Departamento departamento);

    void eliminar(Long id);

    //

    Optional<Departamento> porIdConCliente(Long id);

    void eliminarDepartamentoClientePorId(Long id);

    Optional<Cliente> asignarCliente(Cliente cliente, Long departamentoId);

    Optional<Cliente> crearCliente(Cliente cliente, Long departamentoId);

    Optional<Cliente> eliminarCliente(Cliente cliente, Long departamentoId);
}
