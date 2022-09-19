package com.shirydev.dscatalog.atvum.dto;

import com.shirydev.dscatalog.atvum.entity.Client;

import java.io.Serializable;
import java.time.Instant;

public class ClientDTO implements Serializable {

    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private Instant birthDate;
    private Integer children;


    public ClientDTO(){}

    public ClientDTO(Client entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.income = entity.getIncome();
        this.birthDate = entity.getBirthDate();
        this.children = entity.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
