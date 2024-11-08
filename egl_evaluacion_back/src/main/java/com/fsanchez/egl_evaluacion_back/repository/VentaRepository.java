package com.fsanchez.egl_evaluacion_back.repository;

import com.fsanchez.egl_evaluacion_back.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
