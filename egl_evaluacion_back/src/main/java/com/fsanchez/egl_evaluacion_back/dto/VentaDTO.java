package com.fsanchez.egl_evaluacion_back.dto;

import com.fsanchez.egl_evaluacion_back.entity.Producto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VentaDTO {
    private List<Producto> items;
}
