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
 * Modelo de los estados de los casos
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MBC_ESTADOS_CASOS")
public class EstadoCaso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MBC_ESTADOS_CASOS")
    @SequenceGenerator(name = "SEQ_MBC_ESTADOS_CASOS", sequenceName = "SEQ_MBC_ESTADOS_CASOS", allocationSize = 1)
    @Column(name = "ID_ESTADO_CASO")
    private Integer idEstadoCaso;

    @Column(name = "ESTADO")
    private String estado;

}