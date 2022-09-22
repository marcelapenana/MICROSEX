package com.departamentoexamen.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @Ge
    private Long id;

}
