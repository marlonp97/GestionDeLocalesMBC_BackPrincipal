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
 * Modelo de las ciudades de los locales
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MBC_CIUDADES_LOCALES")
public class CiudadLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MBC_CIUDADES_LOCALES")
    @SequenceGenerator(name = "SEQ_MBC_CIUDADES_LOCALES", sequenceName = "SEQ_MBC_CIUDADES_LOCALES", allocationSize = 1)
    @Column(name = "ID_CIUDAD_LOCAL")
    private Integer idCiudadLocal;

    @Column(name = "CIUDAD")
    private String ciudad;

}