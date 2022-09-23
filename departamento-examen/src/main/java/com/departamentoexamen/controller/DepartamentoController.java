package com.departamentoexamen.controller;

import com.departamentoexamen.models.Cliente;
import com.departamentoexamen.models.entity.Departamento;
import com.departamentoexamen.service.DepartamentoService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class DepartamentoController {
    @Autowired
    private DepartamentoService service;

    @GetMapping
    public ResponseEntity<?> listar(){
        List<Departamento> clientesDepartamentos = service.listar();
        return ResponseEntity.status(HttpStatus.OK).body(clientesDepartamentos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> listar(@PathVariable Long id){
        Optional<Departamento> o= service.porIdConCliente(id);//service.porId(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
            // return ResponseEntity.status(HttpStatus.OK).body(departamentos);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Departamento departamento, BindingResult result){
        if (result.hasErrors()){
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(departamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Departamento departamento, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            return validar(result);
        }
        Optional<Departamento> o = service.porId(id);
        if(o.isPresent()){
            Departamento departamentoDb = o.get();
            departamentoDb.setNombre(departamento.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(departamentoDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Departamento> o = service.porId(id);
        if(o.isPresent()){
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignar-cliente/{departamentoId}")
    public ResponseEntity<?> asignarCliente(@RequestBody Cliente cliente, @PathVariable Long departamentoId){
        Optional<Cliente> o = null;
        try {
            o= service.asignarCliente(cliente, departamentoId);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap
                    ("mensaje", "No existe el cliente por el id -Error en la comunicacion"+ e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/crear-cliente/{departamentoId}")
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente, @PathVariable Long departamentoId){
        Optional<Cliente> o;
        try {
            o= service.crearCliente(cliente, departamentoId);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap
                    ("mensaje", "No se creo el cliente -Error en la comunicacion"+ e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-departamento-cliente/{id}")
    public ResponseEntity<?> eliminarDepartamentoClientePorId(@PathVariable Long id){
        service.eliminarDepartamentoClientePorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/eliminar-cliente/{departamentoId}")
    public ResponseEntity<?> eliminarCliente(@RequestBody Cliente cliente, @PathVariable Long departamentoId){
        Optional<Cliente> o = null;
        try {
            o= service.eliminarCliente(cliente, departamentoId);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap
                    ("mensaje", "No existe el cliente por el id -Error en la comunicacion"+ e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result){
        Map<String, String> errores=new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo "+err.getField()+" "+err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
