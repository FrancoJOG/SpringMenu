package com.example.MenuPOS.Models;

import jakarta.persistence.*;

@Entity //mapear jpa
@Table(name="platillos") //inidcar la tabla
public class PlatilloModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    @Lob // Binaria large object
    @Column(name = "imagen", columnDefinition="MEDIUMBLOB")
    private byte[] imagen;

    public PlatilloModel(String nombre, String descripcion, double precio, byte[] imagenBytes) {

    }

    public PlatilloModel() {

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
