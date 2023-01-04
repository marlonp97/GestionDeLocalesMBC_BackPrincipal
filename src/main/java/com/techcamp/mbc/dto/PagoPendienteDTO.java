package com.techcamp.mbc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO de los pagos pendientes
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PagoPendienteDTO {

    private Long idPago;
    private String descripcion;
    private Float precio;
    private LocalDate fechaVencimiento;
    private Float valorPagado;
    private LocalDate fechaPago;
    private String tipoPago;
    private String estadoPago;
    private String local;

}