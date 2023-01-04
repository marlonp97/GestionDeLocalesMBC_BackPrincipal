package com.techcamp.mbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Modelo de las empresas
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MBC_EMPRESAS")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MBC_EMPRESAS")
    @SequenceGenerator(name = "SEQ_MBC_EMPRESAS", sequenceName = "SEQ_MBC_EMPRESAS", allocationSize = 1)
    @Column(name = "ID_EMPRESA")
    private Long idEmpresa;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DOCUMENTO")
    private String documento;

    @Column(name = "TELEFONO")
    private String telefono;

}