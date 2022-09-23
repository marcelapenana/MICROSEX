package com.clienteexamen.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "departamento-examen",url = "localhost:8002")
public interface DepartamentoClientRest {

    @DeleteMapping("/eliminar-departamento-cliente/{id}")
    void eliminarDepartamentoClientePorId(@PathVariable Long id);
}
