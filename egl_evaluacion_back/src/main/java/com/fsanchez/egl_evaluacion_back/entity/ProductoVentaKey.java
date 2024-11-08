package com.fsanchez.egl_evaluacion_back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
//@Getter
//@Setter
//@EqualsAndHashCode
public class ProductoVentaKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "VENTA_idVENTA")
    private Integer idVenta;

    @Column(name = "PRODUCTOS_idPRODUCTOS")
    private Integer idProducto;

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoVentaKey that = (ProductoVentaKey) o;
        return Objects.equals(idVenta, that.idVenta) && Objects.equals(idProducto, that.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenta, idProducto);
    }
}
