package com.fsanchez.egl_evaluacion_back.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCTOS_VENTA")
public class ProductoVenta {

    @EmbeddedId
    private ProductoVentaKey id = new ProductoVentaKey();

    @ManyToOne
    @MapsId("idVenta")
    @JoinColumn(name = "VENTA_idVENTA")
    private Venta venta;

    @ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name = "PRODUCTOS_idPRODUCTOS")
    private Producto producto;

    private Integer cantidad;

    public ProductoVentaKey getId() {
        return id;
    }

    public void setId(ProductoVentaKey id) {
        this.id = id;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
