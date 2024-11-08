package com.fsanchez.egl_evaluacion_back.repository;

import com.fsanchez.egl_evaluacion_back.entity.ProductoVenta;
import com.fsanchez.egl_evaluacion_back.entity.ProductoVentaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoVentaRepository extends JpaRepository<ProductoVenta, ProductoVentaKey> {
}
