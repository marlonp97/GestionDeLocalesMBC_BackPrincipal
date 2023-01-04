package com.techcamp.mbc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO de los locales
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocalDTO {

    private Long idLocal;
    private String direccion;
    private Float cuotaArriendo;
    private Float cuotaAdministracion;
    private String ciudadLocal;
    private String empresa;

}