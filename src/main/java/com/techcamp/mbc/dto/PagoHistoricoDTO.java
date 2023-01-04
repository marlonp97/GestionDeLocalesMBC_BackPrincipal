package com.techcamp.mbc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO de los pagos históricos
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PagoHistoricoDTO {

    private Long idPago;
    private String descripcion;
    private Float precio;
    private LocalDate fechaVencimiento;
    private String soporte;
    private Float valorPagado;
    private LocalDate fechaPago;
    private String tipoPago;
    private String local;

}