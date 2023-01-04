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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Modelo de los casos
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MBC_CASOS")
public class Caso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MBC_CASOS")
    @SequenceGenerator(name = "SEQ_MBC_CASOS", sequenceName = "SEQ_MBC_CASOS", allocationSize = 1)
    @Column(name = "ID_CASO")
    private Long idCaso;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "SOPORTE")
    private String soporte;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO_CASO")
    private EstadoCaso estadoCaso;

    @OneToOne
    @JoinColumn(name = "ID_PAGO", referencedColumnName = "ID_PAGO")
    private Pago pago;

}