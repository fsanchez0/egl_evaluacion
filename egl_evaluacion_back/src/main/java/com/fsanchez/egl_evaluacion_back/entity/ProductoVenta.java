package com.fsanchez.egl_evaluacion_back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
}
