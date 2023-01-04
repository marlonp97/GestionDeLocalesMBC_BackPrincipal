package com.techcamp.mbc.dto;

import com.techcamp.mbc.model.PagoCaso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO de los casos
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CasoDTO {

    private Long idCaso;
    private String descripcion;
    private String soporte;
    private String estadoCaso;
    private String usuario;
    private PagoCaso pagoCaso;
    private String empresa;
    private String local;

}