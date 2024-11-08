package com.fsanchez.egl_evaluacion_back.service;

import com.fsanchez.egl_evaluacion_back.entity.Producto;
import com.fsanchez.egl_evaluacion_back.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public List<Producto> getProductos(){
        return repository.findAll();
    }

    public Producto getProductoById (Integer id) {
        return repository.findById(id).get();
    }

    public List<Producto> getProductosDisponibles(){
        return repository.getAllByStockGreaterThan(0);
    }

    public Producto getProductoDisponibleById (Integer id) {
        return repository.getProductoByIdAndStockGreaterThan(id, 0);
    }

    public void saveProducto (Producto producto){
        repository.save(producto);
    }

    public void updateProducto (Producto producto){
        repository.save(producto);
    }

    public void deleteProducto (Integer idProducto){
        // Find producto
        Optional<Producto> productoToDelete = repository.findById(idProducto);

        productoToDelete.ifPresent(producto -> repository.delete(producto));
    }

}
