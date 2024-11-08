package com.fsanchez.egl_evaluacion_back.repository;

import com.fsanchez.egl_evaluacion_back.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> getAllByStockGreaterThan(int stock);

    Producto getProductoByIdAndStockGreaterThan(Integer id, int stock);

}
