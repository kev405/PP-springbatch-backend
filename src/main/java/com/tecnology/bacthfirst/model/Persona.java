package com.tecnology.bacthfirst.model;

import javax.persistence.*;

@Entity
@Table(name = "persona")
public class Persona {

    @Column(name = "nombre1")
    private String primerNombre;
    @Column(name = "nombre2")
    private String segundoNombre;

    @Column(name = "telephone")
    private String telefono;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Persona() {
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

    public Persona(String primerNombre, String segundoNombre, String telefono) {
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
