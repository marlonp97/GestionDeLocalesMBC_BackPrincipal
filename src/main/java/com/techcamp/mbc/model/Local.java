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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Modelo de los locales
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MBC_LOCALES")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MBC_LOCALES")
    @SequenceGenerator(name = "SEQ_MBC_LOCALES", sequenceName = "SEQ_MBC_LOCALES", allocationSize = 1)
    @Column(name = "ID_LOCAL")
    private Long idLocal;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "CUOTA_ARRIENDO")
    private Float cuotaArriendo;

    @Column(name = "CUOTA_ADMINISTRACION")
    private Float cuotaAdministracion;

    @ManyToOne
    @JoinColumn(name = "ID_CIUDAD_LOCAL")
    private CiudadLocal ciudadLocal;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

}