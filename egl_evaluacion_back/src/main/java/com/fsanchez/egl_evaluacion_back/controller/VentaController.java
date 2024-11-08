package com.fsanchez.egl_evaluacion_back.controller;

import com.fsanchez.egl_evaluacion_back.dto.VentaDTO;
import com.fsanchez.egl_evaluacion_back.service.VentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@Slf4j
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    @GetMapping(path = "/all")
    public ResponseEntity<?> getAllVentas () {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllVentas());
    }

    @PostMapping(path = "/new")
    public ResponseEntity<?> newVenta (@RequestBody VentaDTO venta){

        Map<String, Object> response = new HashMap<String, Object>();
        List<String> errors = new ArrayList<String>();
        boolean error = false;

        try {
            service.processVenta(venta);
        }catch (Exception e) {
            errors.add(e.getCause().getMessage());
            error = true;
        }

        response.put("errors", errors);

        return ResponseEntity.status(error?HttpStatus.INTERNAL_SERVER_ERROR:HttpStatus.OK).body(response);
    }

}
