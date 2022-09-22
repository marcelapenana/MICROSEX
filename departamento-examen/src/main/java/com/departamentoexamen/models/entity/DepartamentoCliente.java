package com.departamentoexamen.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "departamentos_clientes")
public class DepartamentoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id", unique = true)
    private Long clienteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj){
            return true;
        }
        if (!(obj instanceof DepartamentoCliente)){
            return false;
        }
        DepartamentoCliente o =(DepartamentoCliente) obj;
        return this.clienteId!=null && this.clienteId.equals(o.clienteId);
    }
}
