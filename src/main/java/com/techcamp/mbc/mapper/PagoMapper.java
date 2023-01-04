package com.techcamp.mbc.mapper;

import com.techcamp.mbc.dto.PagoDTO;
import com.techcamp.mbc.model.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper de los pagos
 * @author Marlon Pérez Ríos
 */
@Mapper(componentModel = "spring")
public interface PagoMapper {

    /**
     * Método para convertir modelo del pago a DTO
     * @param pago Pago
     * @return DTO del pago
     */
    @Mappings({
            @Mapping(source = "idPago", target = "idPago"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "fechaVencimiento", target = "fechaVencimiento"),
            @Mapping(source = "soporte", target = "soporte"),
            @Mapping(source = "valorPagado", target = "valorPagado"),
            @Mapping(source = "fechaPago", target = "fechaPago"),
            @Mapping(source = "tipoPago", target = "tipoPago"),
            @Mapping(source = "estadoPago", target = "estadoPago"),
            @Mapping(source = "usuario.idUsuario", target = "usuarioPago.idUsuario"),
            @Mapping(source = "usuario.correo", target = "usuarioPago.correo"),
            @Mapping(source = "empresa.idEmpresa", target = "empresaPago.idEmpresa"),
            @Mapping(source = "empresa.nombre", target = "empresaPago.nombre"),
            @Mapping(source = "local.idLocal", target = "localPago.idLocal"),
            @Mapping(source = "local.direccion", target = "localPago.direccion")
    })
    PagoDTO pagoToPagoDTO(Pago pago);
    /**
     * Método para convertir lista de modelos de pagos a lista DTO
     * @param pago Lista de pagos
     * @return Lista DTO de los pagos
     */
    List<PagoDTO> pagoToPagoDTOList(List<Pago> pago);

}