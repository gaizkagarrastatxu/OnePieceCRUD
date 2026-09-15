package com.onepiece.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "piratas") // Nombre de la tabla en tu MySQL Workbench
public class Pirata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "banda", length = 100)
    private String banda;

    @Column(name = "recompensa")
    private Long recompensa;

    @Column(name = "fruta_del_diablo", length = 100)
    private String frutaDelDiablo;

    // Constructores
    public Pirata() {
    }

    public Pirata(String nombre, String banda, Long recompensa, String frutaDelDiablo) {
        this.nombre = nombre;
        this.banda = banda;
        this.recompensa = recompensa;
        this.frutaDelDiablo = frutaDelDiablo;
    }

    // Getters y Setters
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

    public String getBanda() {
        return banda;
    }

    public void setBanda(String banda) {
        this.banda = banda;
    }

    public Long getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(Long recompensa) {
        this.recompensa = recompensa;
    }

    public String getFrutaDelDiablo() {
        return frutaDelDiablo;
    }

    public void setFrutaDelDiablo(String frutaDelDiablo) {
        this.frutaDelDiablo = frutaDelDiablo;
    }

    @Override
    public String toString() {
        return "Pirata [id=" + id + ", nombre=" + nombre + ", banda=" + banda 
                + ", recompensa=" + recompensa + ", frutaDelDiablo=" + frutaDelDiablo + "]";
    }
}