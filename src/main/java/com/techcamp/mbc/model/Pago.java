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
import java.time.LocalDate;

/**
 * Modelo de los pagos
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MBC_PAGOS")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MBC_PAGOS")
    @SequenceGenerator(name = "SEQ_MBC_PAGOS", sequenceName = "SEQ_MBC_PAGOS", allocationSize = 1)
    @Column(name = "ID_PAGO")
    private Long idPago;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "PRECIO")
    private Float precio;

    @Column(name = "FECHA_VENCIMIENTO")
    private LocalDate fechaVencimiento;

    @Column(name = "SOPORTE")
    private String soporte;

    @Column(name = "VALOR_PAGADO")
    private Float valorPagado;

    @Column(name = "FECHA_PAGO")
    private LocalDate fechaPago;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_PAGO")
    private TipoPago tipoPago;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO_PAGO")
    private EstadoPago estadoPago;

    @ManyToOne
    @JoinColumn(name = "ID_LOCAL")
    private Local local;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

}