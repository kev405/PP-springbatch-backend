package com.tecnology.bacthfirst.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class persona {

    private String primerNombre;
    private String segundoNombre;
    private String telefono;
    private Long id;

    public persona() {
        super();
    }

    @Override
    public String toString() {
        return "persona{" +
                "primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public persona(String primerNombre, String segundoNombre, String telefono) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.telefono = telefono;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
}
