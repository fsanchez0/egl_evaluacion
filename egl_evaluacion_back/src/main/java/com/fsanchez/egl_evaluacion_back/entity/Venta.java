package com.fsanchez.egl_evaluacion_back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VENTA")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVENTA")
    private Integer id;

    private Date fecha;

    @Column(name = "totalventa")
    private Integer total;

    //@OneToMany(mappedBy = "venta")
    //@JsonIgnore
    //Set<ProductoVenta> ratings;
}
