package com.techcamp.mbc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO de las empresas con la información reducida
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpresaPagoDTO {

    private Long idEmpresa;
    private String nombre;

}