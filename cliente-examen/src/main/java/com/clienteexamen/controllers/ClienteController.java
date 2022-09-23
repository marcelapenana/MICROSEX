package com.clienteexamen.controllers;

import com.clienteexamen.modelsentity.Cliente;
import com.clienteexamen.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    public Map< String, List<Cliente>> listar(){
        return Collections.singletonMap("usuarios", service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Cliente> usuarioOptional = service.porId(id);
        if (usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Cliente cliente, BindingResult result){
        if (result.hasErrors()){
            return validar(result);
        }
        if (!cliente.getEmail().isEmpty() && service.existePorEmail(cliente.getEmail())){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje","Ya existe un usuario con ese email"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cliente));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Cliente cliente,BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            return validar(result);
        }
        Optional<Cliente> o = service.porId(id);
        if (o.isPresent()){
            Cliente clienteDb = o.get();
            if (!cliente.getEmail().isEmpty() &&
                    !cliente.getEmail().equalsIgnoreCase(clienteDb.getEmail()) &&
                    service.porEmail(cliente.getEmail()).isPresent()){
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("Mensaje", "Ya existe un usuario con este correo"));
            }
            clienteDb.setNombre(cliente.getNombre());
            clienteDb.setApellido(cliente.getApellido());
            clienteDb.setEmail(cliente.getEmail());
            clienteDb.setPassword(cliente.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(clienteDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Cliente> o = service.porId(id);
        if (o.isPresent()){
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/clientes-por-departamento")
    public ResponseEntity<?> obtenerClientesPorDepartamento(@RequestParam List<Long>ids){
        return ResponseEntity.ok(service.listarPorIds(ids));
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result){
        Map<String, String> errores=new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo "+err.getField()+" "+err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
