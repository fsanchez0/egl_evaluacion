package com.fsanchez.egl_evaluacion_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Entity
@Getter
@Setter
@SpringBootApplication
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCTOS")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPRODUCTOS")
    private Integer id;

    private String nombre;

    private Integer costo;

    private Integer stock;

}
