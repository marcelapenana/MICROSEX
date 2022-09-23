package com.departamentoexamen.models.entity;

import com.departamentoexamen.models.Cliente;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "departamento_id")
    private List<DepartamentoCliente> departamentoClientes;

    @Transient
    //referencia a la clase cliente en models
    private List<Cliente> clientes;


    public Departamento() {
        departamentoClientes = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addDepartamentoCliente(DepartamentoCliente departamentoCliente){departamentoClientes.add(departamentoCliente);}
    public void removeDepartamentoCliente(DepartamentoCliente  departamentoCliente ){departamentoClientes.remove(departamentoCliente);}


    public List<DepartamentoCliente> getDepartamentoClientes() {
        return departamentoClientes;
    }

    public void setDepartamentoClientes(List<DepartamentoCliente> departamentoClientes) {
        this.departamentoClientes = departamentoClientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
