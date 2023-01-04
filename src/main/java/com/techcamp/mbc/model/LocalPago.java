package com.techcamp.mbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Modelo de los locales con la información reducida
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocalPago {

    private Long idLocal;
    private String direccion;

}