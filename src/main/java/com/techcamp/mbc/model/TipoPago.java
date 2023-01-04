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
 * Modelo de los tipos de pago
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MBC_TIPOS_PAGOS")
public class TipoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MBC_TIPOS_PAGOS")
    @SequenceGenerator(name = "SEQ_MBC_TIPOS_PAGOS", sequenceName = "SEQ_MBC_TIPOS_PAGOS", allocationSize = 1)
    @Column(name = "ID_TIPO_PAGO")
    private Integer idTipoPago;

    @Column(name = "TIPO")
    private String tipo;

}