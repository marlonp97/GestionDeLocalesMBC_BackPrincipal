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
 * Modelo de los roles de los usuarios
 * @author Marlon Pérez Ríos
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MBC_ROLES_USUARIOS")
public class RolUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MBC_ROLES_USUARIOS")
    @SequenceGenerator(name = "SEQ_MBC_ROLES_USUARIOS", sequenceName = "SEQ_MBC_ROLES_USUARIOS", allocationSize = 1)
    @Column(name = "ID_ROL_USUARIO")
    private Integer idRolUsuario;

    @Column(name = "ROL")
    private String rol;

}