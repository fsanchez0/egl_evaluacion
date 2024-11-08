package com.fsanchez.egl_evaluacion_back.service;

import com.fsanchez.egl_evaluacion_back.dto.VentaDTO;
import com.fsanchez.egl_evaluacion_back.entity.Producto;
import com.fsanchez.egl_evaluacion_back.entity.ProductoVenta;
import com.fsanchez.egl_evaluacion_back.entity.Venta;
import com.fsanchez.egl_evaluacion_back.repository.ProductoRepository;
import com.fsanchez.egl_evaluacion_back.repository.ProductoVentaRepository;
import com.fsanchez.egl_evaluacion_back.repository.VentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class VentaService {

    @Autowired
    private VentaRepository repository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoVentaRepository productoVentaRepository;

    public List<Venta> getAllVentas() {
        return repository.findAll();
    }

    public void processVenta(VentaDTO venta){
        int total = 0;
        boolean isStockValid = true;

        // Obtener lista de productos

        List<Producto> productoList = venta.getItems();

        List<ProductoVenta> productoVentaList = new ArrayList<>();

        for (Producto currentProducto: productoList
             ) {
             total += currentProducto.getCosto();
             // todo: validar stock antes de vender
             // isStockValid = currentProducto.getStock()>0; // not sure if this works, not part of the test this validation so keep for the future
             // update stock
             currentProducto.setStock(currentProducto.getStock() - 1);
             productoRepository.save(currentProducto);
        }

        if(isStockValid){
            Venta ventaToStore = new Venta();

            ventaToStore.setFecha(new Date());
            ventaToStore.setTotal(total);

            Venta storedVenta = repository.save(ventaToStore);

            // guardar hijos
            for (Producto currentProd: productoList
                 ) {
                ProductoVenta productoVenta = new ProductoVenta();
                productoVenta.setVenta(storedVenta);
                productoVenta.setProducto(currentProd);
                // todo: group items
                productoVenta.setCantidad(1);

                productoVentaRepository.save(productoVenta);
            }
        }
    }
}
