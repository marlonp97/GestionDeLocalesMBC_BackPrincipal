package com.techcamp.mbc.mapper;

import com.techcamp.mbc.dto.UsuarioDTO;
import com.techcamp.mbc.dto.UsuarioPagoDTO;
import com.techcamp.mbc.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper de los usuarios
 * @author Marlon Pérez Ríos
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    /**
     * Método para convertir modelo del usuario a DTO
     * @param usuario Usuario
     * @return DTO del usuario
     */
    @Mappings({
            @Mapping(source = "idUsuario", target = "idUsuario"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "correo", target = "correo"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "rolUsuario.rol", target = "rolUsuario"),
            @Mapping(source = "empresa.nombre", target = "empresa")
    })
    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
    /**
     * Método para convertir lista de modelos de usuarios a lista DTO
     * @param usuario Lista de usuarios
     * @return Lista DTO de los usuarios
     */
    List<UsuarioDTO> usuarioToUsuarioDTOList(List<Usuario> usuario);

    /**
     * Método para convertir modelo del usuario a DTO con la información reducida
     * @param usuario Usuario
     * @return DTO del usuario con la información reducida
     */
    @Mappings({
            @Mapping(source = "idUsuario", target = "idUsuario"),
            @Mapping(source = "correo", target = "correo")
    })
    UsuarioPagoDTO usuarioToUsuarioPagoDTO(Usuario usuario);
    /**
     * Método para convertir lista de modelos de usuarios a lista DTO con la información reducida
     * @param usuario Lista de usuario
     * @return Lista DTO de los usuarios con la información reducida
     */
    List<UsuarioPagoDTO> usuarioToUsuarioPagoDTOList(List<Usuario> usuario);

}