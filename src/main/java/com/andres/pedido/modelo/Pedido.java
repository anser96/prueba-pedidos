//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.andres.pedido.modelo;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(
        name = "pedido"
)
public class Pedido {
    @Id
    @SequenceGenerator(
            name = "pedido_sequence",
            sequenceName = "pedido_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pedido_sequence"
    )
    private Long id;
    private String descripcion;
    private LocalDateTime fecha;
    private int cedula;
    private String direccion;
    private float domicilio;
    private float total;
    private float iva;
    private String producto;
    private float precioProducto;

    public String getProducto() {
        return this.producto;
    }

    public float getPrecioProducto() {
        return this.precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public float getIva() {
        return this.iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public Pedido() {
    }

    public float getDomicilio() {
        return this.domicilio;
    }

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setDomicilio(float domicilio) {
        this.domicilio = domicilio;
    }

    public Pedido(String descripcion, LocalDateTime fecha, int cedula, String direccion, float domicilio, float total, float iva, String producto, float precioProducto) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.cedula = cedula;
        this.direccion = direccion;
        this.domicilio = domicilio;
        this.total = total;
        this.iva = iva;
        this.producto = producto;
        this.precioProducto = precioProducto;
    }

    public Pedido(Long id, String descripcion, LocalDateTime fecha, int cedula, String direccion, float domicilio, float total) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.cedula = cedula;
        this.direccion = direccion;
        this.domicilio = domicilio;
        this.total = total;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getCedula() {
        return this.cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
