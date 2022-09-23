package com.departamentoexamen.clients;

import com.departamentoexamen.models.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cliente-examen",url = "cliente-examen:8001")
public interface ClienteClientRest {

    @GetMapping("/{id}")
    public Cliente detalle(@PathVariable Long id);

    @PostMapping("/")
    Cliente crear(@RequestBody Cliente cliente);//cliente de models

    @GetMapping("/clientes-por-departamento")
    List<Cliente> obtenerClientesPorDepartamento(@RequestParam Iterable<Long> ids);
}
