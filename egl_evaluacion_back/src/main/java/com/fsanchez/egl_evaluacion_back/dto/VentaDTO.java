package com.fsanchez.egl_evaluacion_back.dto;

import com.fsanchez.egl_evaluacion_back.entity.Producto;

import java.util.List;

public class VentaDTO {
    private List<Producto> items;

    public List<Producto> getItems() {
        return items;
    }

    public void setItems(List<Producto> items) {
        this.items = items;
    }
}
