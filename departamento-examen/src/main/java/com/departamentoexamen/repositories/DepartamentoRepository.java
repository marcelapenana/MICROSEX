package com.departamentoexamen.repositories;

import com.departamentoexamen.models.entity.Departamento;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DepartamentoRepository extends CrudRepository<Departamento, Long> {
    @Modifying
    @Query("delete from DepartamentoCliente cu where cu.clienteId=?1")
    void eliminarDepartamentoClientePorId(Long id);
}
