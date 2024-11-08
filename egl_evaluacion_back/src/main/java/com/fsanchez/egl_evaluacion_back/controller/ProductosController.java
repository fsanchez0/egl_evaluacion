package com.fsanchez.egl_evaluacion_back.controller;

import com.fsanchez.egl_evaluacion_back.entity.Producto;
import com.fsanchez.egl_evaluacion_back.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private ProductoService service;

    @GetMapping(path = "/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.status(HttpStatus.OK).body("It's working!");
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getProductos());
    }

    @GetMapping(path = "/allDisponible", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAvailableProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getProductosDisponibles());
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getProductoById(id));
    }

    @GetMapping(path = "/getDisponible/{id}")
    public ResponseEntity<?> getProductoDisponibleById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getProductoDisponibleById(id));
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> createProducto(@RequestBody Producto producto){
        Map<String, Object> response = new HashMap<String, Object>();
        List<String> errors = new ArrayList<String>();
        boolean error = false;

        try {
            service.saveProducto(producto);
        }catch (Exception e) {
            errors.add(e.getCause().getMessage());
            error = true;
        }

        response.put("errors", errors);

        return ResponseEntity.status(error?HttpStatus.INTERNAL_SERVER_ERROR:HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> updateProducto(@RequestBody Producto producto){
        Map<String, Object> response = new HashMap<String, Object>();
        List<String> errors = new ArrayList<String>();
        boolean error = false;
        if (null == producto.getId() ) {
            errors.add("need product ID");
            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        try {
            service.updateProducto(producto);
        } catch (Exception e) {
            errors.add(e.getCause().getMessage());
            response.put("errors", errors);
            error = true;
        }
        //response.put("course", courseIn);
        return ResponseEntity.status(error?HttpStatus.INTERNAL_SERVER_ERROR:HttpStatus.OK).body(errors);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById (@PathVariable Integer id){
        Map<String, Object> response = new HashMap<String, Object>();
        List<String> errors = new ArrayList<String>();
        if (null == id ) {
            errors.add("need ID");
            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        try {
            service.deleteProducto(id);
        } catch (Exception e) {
            errors.add("Product with ID: " + id + " does not exist");
            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        return ResponseEntity.status(HttpStatus.OK).body(errors);
    }

}
