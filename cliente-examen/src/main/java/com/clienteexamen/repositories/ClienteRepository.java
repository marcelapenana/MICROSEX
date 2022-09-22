package com.clienteexamen.repositories;

import com.clienteexamen.modelsentity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    @Query("Select u from Cliente u where u.email=?1")
    Optional<Cliente>porEmail(String email);

    boolean existsByEmail(String email);
}
