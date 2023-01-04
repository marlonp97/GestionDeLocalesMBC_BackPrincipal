package com.techcamp.mbc.service;

import com.itextpdf.text.Document;
import com.techcamp.mbc.dto.PagoDTO;
import com.techcamp.mbc.dto.PagoHistoricoDTO;
import com.techcamp.mbc.dto.PagoPendienteDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del servicio para el manejo de los pagos
 * @author Marlon Pérez Ríos
 */
public interface PagoService {

    /**
     * Método para devolver todos los pagos
     * @return Lista de pagos
     */
    List<PagoDTO> getAllPagos() throws Exception;

    /**
     * Método para crear un pago
     * @param pagoDTO Pago
     * @return Pago creado
     */
    PagoDTO createPago(PagoDTO pagoDTO) throws Exception;

    /**
     * Método para devolver un pago al buscarlo por su ID
     * @param idPago ID del pago
     * @return Pago
     */
    Optional<PagoDTO> getPagoById(Long idPago) throws Exception;

    /**
     * Método para actualizar un pago al buscarlo por su ID
     * @param idPago ID del pago
     * @param pagoDTO Pago
     * @return Pago actualizado
     */
    PagoDTO updatePago(Long idPago, PagoDTO pagoDTO) throws Exception;

    /**
     * Método para eliminar un pago al buscarlo por su ID
     * @param idPago ID del pago
     * @return Confirmación de la eliminación del pago
     */
    Boolean deletePagoById(Long idPago) throws Exception;

    /**
     * Método para devolver todos los pagos finalizados de un usuario al buscar por su ID
     * @param idUsuario ID del usuario
     * @return Lista de pagos finalizados
     */
    List<PagoHistoricoDTO> getPagosHistoricoByIdUsuario(Long idUsuario) throws Exception;

    /**
     * Método para devolver todos los pagos pendientes de un usuario al buscar por su ID
     * @param idUsuario ID del usuario
     * @return Lista de pagos pendientes
     */
    List<PagoPendienteDTO> getPagosPendientesByIdUsuario(Long idUsuario) throws Exception;

    /**
     * Método para devolver el paz y salvo de un usuario al buscar por su ID
     * @param idUsuario ID del usuario
     * @return Paz y salvo
     */
    Document generarPazYSalvo(Long idUsuario) throws Exception;

    /**
     * Método para aprobar un pago al buscarlo por su ID
     * @param idPago ID del pago
     * @param pagoDTO Pago
     * @return Pago
     */
    PagoDTO aprobarPago(Long idPago, PagoDTO pagoDTO) throws Exception;

    /**
     * Método para crear pagos de forma masiva subiendo un archivo CSV
     * @param archivo Archivo a subir
     * @return Lista de pagos creados
     */
    List<PagoDTO> createPagosCSV(MultipartFile archivo) throws Exception;

}