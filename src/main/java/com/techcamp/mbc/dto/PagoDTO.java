package com.techcamp.mbc.dto;

import com.techcamp.mbc.model.EmpresaPago;
import com.techcamp.mbc.model.EstadoPago;
import com.techcamp.mbc.model.LocalPago;
import com.techcamp.mbc.model.TipoPago;
import com.techcamp.mbc.model.UsuarioPago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO de los pagos
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PagoDTO {

    private Long idPago;
    private String descripcion;
    private Float precio;
    private LocalDate fechaVencimiento;
    private String soporte;
    private Float valorPagado;
    private LocalDate fechaPago;
    private TipoPago tipoPago;
    private EstadoPago estadoPago;
    private UsuarioPago usuarioPago;
    private EmpresaPago empresaPago;
    private LocalPago localPago;

}