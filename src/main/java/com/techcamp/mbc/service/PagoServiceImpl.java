package com.techcamp.mbc.service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.techcamp.mbc.dto.PagoDTO;
import com.techcamp.mbc.dto.PagoHistoricoDTO;
import com.techcamp.mbc.dto.PagoPendienteDTO;
import com.techcamp.mbc.exception.PendingPaymentsException;
import com.techcamp.mbc.mapper.PagoHistoricoMapper;
import com.techcamp.mbc.mapper.PagoMapper;
import com.techcamp.mbc.mapper.PagoPendienteMapper;
import com.techcamp.mbc.model.Empresa;
import com.techcamp.mbc.model.EstadoPago;
import com.techcamp.mbc.model.Local;
import com.techcamp.mbc.model.Pago;
import com.techcamp.mbc.model.TipoPago;
import com.techcamp.mbc.model.Usuario;
import com.techcamp.mbc.repository.EmpresaRepository;
import com.techcamp.mbc.repository.EstadoPagoRepository;
import com.techcamp.mbc.repository.LocalRepository;
import com.techcamp.mbc.repository.PagoRepository;
import com.techcamp.mbc.repository.TipoPagoRepository;
import com.techcamp.mbc.repository.UsuarioRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Servicio para el manejo de los pagos
 * @author Marlon Pérez Ríos
 */
@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private TipoPagoRepository tipoPagoRepository;

    @Autowired
    private EstadoPagoRepository estadoPagoRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PagoMapper pagoMapper;

    @Autowired
    private PagoHistoricoMapper pagoHistoricoMapper;

    @Autowired
    private PagoPendienteMapper pagoPendienteMapper;

    public PagoServiceImpl(PagoRepository pagoRepository, TipoPagoRepository tipoPagoRepository,
                           EstadoPagoRepository estadoPagoRepository, LocalRepository localRepository,
                           EmpresaRepository empresaRepository, UsuarioRepository usuarioRepository,
                           PagoMapper pagoMapper, PagoHistoricoMapper pagoHistoricoMapper,
                           PagoPendienteMapper pagoPendienteMapper) {

        this.pagoRepository = pagoRepository;
        this.tipoPagoRepository = tipoPagoRepository;
        this.estadoPagoRepository = estadoPagoRepository;
        this.localRepository = localRepository;
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
        this.pagoMapper = pagoMapper;
        this.pagoHistoricoMapper = pagoHistoricoMapper;
        this.pagoPendienteMapper = pagoPendienteMapper;

    }

    /**
     * Método para devolver todos los pagos
     * @return Lista de pagos
     */
    @Override
    public List<PagoDTO> getAllPagos() throws Exception {

        try {

            return pagoMapper.pagoToPagoDTOList(pagoRepository.findAll());

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para crear un pago
     * @param pagoDTO Pago
     * @return Pago creado
     */
    @Override
    public PagoDTO createPago(PagoDTO pagoDTO) throws Exception {

        try {

            TipoPago tipoPagoDB = tipoPagoRepository.findByTipo(pagoDTO.getTipoPago().getTipo()).orElse(null);
            EstadoPago estadoPagoDB = estadoPagoRepository.findByEstado(pagoDTO.getEstadoPago().getEstado()).orElse(null);
            Local localDB = localRepository.findByDireccion(pagoDTO.getLocalPago().getDireccion()).orElse(null);
            Empresa empresaDB = empresaRepository.findByNombre(pagoDTO.getEmpresaPago().getNombre()).orElse(null);
            Usuario usuarioDB = usuarioRepository.findByCorreo(pagoDTO.getUsuarioPago().getCorreo()).orElse(null);

            if((tipoPagoDB != null) && (estadoPagoDB != null) && (localDB != null)) {

                // Crear pago si la información ingresada es correcta
                Pago pago = new Pago();
                pago.setDescripcion(pagoDTO.getDescripcion());
                pago.setPrecio(pagoDTO.getPrecio());
                pago.setFechaVencimiento(pagoDTO.getFechaVencimiento());
                pago.setSoporte(pagoDTO.getSoporte());
                pago.setValorPagado(null);
                pago.setFechaPago(pagoDTO.getFechaPago());
                pago.setTipoPago(tipoPagoDB);
                pago.setEstadoPago(estadoPagoDB);
                pago.setLocal(localDB);
                pago.setEmpresa(empresaDB);
                pago.setUsuario(usuarioDB);

                return pagoMapper.pagoToPagoDTO(pagoRepository.save(pago));

            } else {

                return null;

            }

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para devolver un pago al buscarlo por su ID
     * @param idPago ID del pago
     * @return Pago
     */
    @Override
    public Optional<PagoDTO> getPagoById(Long idPago) throws Exception {

        try {

            return Optional.ofNullable(pagoMapper.pagoToPagoDTO(pagoRepository.findById(idPago).orElse(null)));

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para actualizar un pago al buscarlo por su ID
     * @param idPago ID del pago
     * @param pagoDTO Pago
     * @return Pago actualizado
     */
    @Override
    public PagoDTO updatePago(Long idPago, PagoDTO pagoDTO) throws Exception {

        try {

            Pago pagoDB = pagoRepository.findById(idPago).orElse(null);

            if(pagoDB != null) {

                TipoPago tipoPagoDB = tipoPagoRepository.findByTipo(
                        (pagoDTO.getTipoPago() != null) ?
                                pagoDTO.getTipoPago().getTipo() : null).orElse(null);
                EstadoPago estadoPagoDB = estadoPagoRepository.findByEstado(
                        (pagoDTO.getEstadoPago() != null) ?
                                pagoDTO.getEstadoPago().getEstado() : null).orElse(null);
                Local localDB = localRepository.findByDireccion(
                        (pagoDTO.getLocalPago() != null) ?
                                pagoDTO.getLocalPago().getDireccion() : null).orElse(null);
                Empresa empresaDB = empresaRepository.findByNombre(
                        (pagoDTO.getEmpresaPago() != null) ?
                                pagoDTO.getEmpresaPago().getNombre() : null).orElse(null);
                Usuario usuarioDB = usuarioRepository.findByCorreo(
                        (pagoDTO.getUsuarioPago() != null) ?
                                pagoDTO.getUsuarioPago().getCorreo() : null).orElse(null);

                if((tipoPagoDB != null) && (estadoPagoDB != null) && (localDB != null)) {

                    // Actualizar pago si el pago existe y si la información ingresada es correcta
                    pagoDB.setDescripcion(pagoDTO.getDescripcion());
                    pagoDB.setPrecio(pagoDTO.getPrecio());
                    pagoDB.setFechaVencimiento(pagoDTO.getFechaVencimiento());
                    pagoDB.setSoporte(pagoDTO.getSoporte());
                    pagoDB.setValorPagado(pagoDTO.getValorPagado());
                    pagoDB.setFechaPago(pagoDTO.getFechaPago());
                    pagoDB.setTipoPago(tipoPagoDB);
                    pagoDB.setEstadoPago(estadoPagoDB);
                    pagoDB.setUsuario(usuarioDB);
                    pagoDB.setEmpresa(empresaDB);
                    pagoDB.setLocal(localDB);

                    return pagoMapper.pagoToPagoDTO(pagoRepository.save(pagoDB));

                } else {

                    return null;

                }

            } else {

                return null;

            }

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para eliminar un pago al buscarlo por su ID
     * @param idPago ID del pago
     * @return Confirmación de la eliminación del pago
     */
    @Override
    public Boolean deletePagoById(Long idPago) throws Exception {

        try {

            Optional<Pago> pago = pagoRepository.findById(idPago);

            if(pago.isPresent()) {

                pagoRepository.deleteById(idPago);

                return true;

            } else {

                return false;

            }

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para devolver todos los pagos finalizados de un usuario al buscar por su ID
     * @param idUsuario ID del usuario
     * @return Lista de pagos finalizados
     */
    @Override
    public List<PagoHistoricoDTO> getPagosHistoricoByIdUsuario(Long idUsuario) throws Exception {

        try {

            Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

            if(usuario.isPresent()) {

                if(usuario.get().getEmpresa() != null) {

                    // Devolver lista de pagos finalizados si el usuario existe
                    return pagoHistoricoMapper.pagoToPagoHistoricoDTOList(pagoRepository.findHistoricoByIdEmpresa(usuario.get().getEmpresa().getIdEmpresa()));

                } else {

                    return List.of();

                }

            } else {

                return null;

            }

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para devolver todos los pagos pendientes de un usuario al buscar por su ID
     * @param idUsuario ID del usuario
     * @return Lista de pagos pendientes
     */
    @Override
    public List<PagoPendienteDTO> getPagosPendientesByIdUsuario(Long idUsuario) throws Exception {

        try {

            Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

            if(usuario.isPresent()) {

                if(usuario.get().getEmpresa() != null) {

                    // Devolver lista de pagos pendientes si el usuario existe y pertenece a una empresa
                    return pagoPendienteMapper.pagoToPagoPendienteDTOList(pagoRepository.findPendientesByIdEmpresa(usuario.get().getEmpresa().getIdEmpresa()));

                } else {

                    return List.of();

                }

            } else {

                return null;

            }

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para devolver el paz y salvo de un usuario al buscar por su ID
     * @param idUsuario ID del usuario
     * @return Paz y salvo
     */
    @Override
    public Document generarPazYSalvo(Long idUsuario) throws PendingPaymentsException, Exception {

        try {

            Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

            if(usuario.isPresent()) {

                if(usuario.get().getEmpresa() != null) {

                    // Devolver lista de pagos pendientes si el usuario existe y pertenece a una empresa
                    List<Pago> pagosPendientes = pagoRepository.findPendientesByIdEmpresa(usuario.get().getEmpresa().getIdEmpresa());

                    if(pagosPendientes.isEmpty()) {

                        // Obtener fecha actual para colocarla en el paz y salvo
                        DateTimeFormatter formatoTiempo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate fechaActual = LocalDate.now();
                        String tiempoActualFormateado = fechaActual.format(formatoTiempo);

                        // Obtener nombre y documento de la empresa para colocarlos en el paz y salvo
                        String nombreEmpresa = usuario.get().getEmpresa().getNombre();
                        String documentoEmpresa = usuario.get().getEmpresa().getDocumento();

                        String nombreCompletoArchivo = "Paz y salvo - " + nombreEmpresa + " - " + tiempoActualFormateado + ".pdf";

                        // El paz y salvo se guarda en carpeta "downloads" en la carpeta raiz del proyecto
                        Path directorioArchivos = Paths.get("downloads");
                        Path rutaArchivo = directorioArchivos.resolve(nombreCompletoArchivo);

                        // Crear paz y salvo
                        Document documento = new Document(PageSize.LETTER, 85, 85, 85, 85);
                        OutputStream outputStream = new FileOutputStream(rutaArchivo.toFile());

                        PdfWriter.getInstance(documento, outputStream);

                        documento.open();

                        int dia = fechaActual.getDayOfMonth();
                        Month mes = fechaActual.getMonth();
                        String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
                        int anio = fechaActual.getYear();
                        String fecha = dia + " de " + nombreMes + " de " + anio;
                        documento.add(new Paragraph(fecha));

                        documento.add(Chunk.NEWLINE);
                        documento.add(Chunk.NEWLINE);
                        documento.add(Chunk.NEWLINE);
                        documento.add(Chunk.NEWLINE);
                        documento.add(Chunk.NEWLINE);

                        Paragraph titulo = new Paragraph("PAZ Y SALVO");
                        titulo.setAlignment(Element.ALIGN_CENTER);
                        documento.add(titulo);

                        documento.add(Chunk.NEWLINE);
                        documento.add(Chunk.NEWLINE);

                        String textoParrafo1 = "Se certifica que, la empresa " + nombreEmpresa
                                + " identificada con documento " + documentoEmpresa
                                + ", se encuentra a paz y salvo por concepto de pagos de administración de locales que tiene arrendados a su nombre.";
                        Paragraph parrafo1 = new Paragraph(textoParrafo1);
                        parrafo1.setAlignment(Element.ALIGN_JUSTIFIED);
                        documento.add(parrafo1);

                        documento.add(Chunk.NEWLINE);

                        String textoParrafo2 = "El presente certificado se expide por solicitud de la empresa interesada el día "
                                + dia + " del mes de " + nombreMes + " del año " + anio + ".";
                        Paragraph parrafo2 = new Paragraph(textoParrafo2);
                        parrafo2.setAlignment(Element.ALIGN_JUSTIFIED);
                        documento.add(parrafo2);

                        documento.add(Chunk.NEWLINE);
                        documento.add(Chunk.NEWLINE);
                        documento.add(Chunk.NEWLINE);
                        documento.add(Chunk.NEWLINE);
                        documento.add(Chunk.NEWLINE);

                        documento.add(new Paragraph("Cordialmente,"));

                        documento.add(Chunk.NEWLINE);

                        documento.add(new Paragraph("Administrador Sistema de gestión de locales MBC."));

                        documento.close();
                        outputStream.close();

                        return documento;

                    } else {

                        throw new PendingPaymentsException();

                    }

                } else {

                    return null;

                }

            } else {

                return null;

            }

        } catch (PendingPaymentsException e) {

            // Lanzar excepción si el usuario tiene pagos pendientes
            throw new PendingPaymentsException();

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para aprobar un pago al buscarlo por su ID
     * @param idPago ID del pago
     * @param pagoDTO Pago
     * @return Pago
     */
    @Override
    public PagoDTO aprobarPago(Long idPago, PagoDTO pagoDTO) throws Exception {

        try {

            Pago pagoDB = pagoRepository.findById(idPago).orElse(null);

            if(pagoDB != null) {

                EstadoPago estadoPagoDB = estadoPagoRepository.findByEstado(pagoDTO.getEstadoPago().getEstado()).orElse(null);
                Usuario usuarioDB = usuarioRepository.findByCorreo(pagoDTO.getUsuarioPago().getCorreo()).orElse(null);
                Empresa empresaDB = empresaRepository.findByNombre(pagoDTO.getEmpresaPago().getNombre()).orElse(null);

                if((estadoPagoDB.getEstado().equals("Revisión")) && (usuarioDB != null)
                        && (empresaDB != null) && (pagoDTO.getValorPagado() != null)
                        && (pagoDTO.getFechaPago() != null) && (pagoDTO.getSoporte() != null)) {

                    // Calcular fechas de referencia si el pago existe y si la información que contiene es correcta
                    int mesVencimiento = pagoDB.getFechaVencimiento().getMonthValue();
                    int anioVencimiento = pagoDB.getFechaVencimiento().getYear();
                    int diaAdministracion = 10;
                    int diaArriendo = 20;

                    LocalDate fechaArriendo = LocalDate.of(anioVencimiento, mesVencimiento, diaArriendo);
                    LocalDate fechaAdministracion = LocalDate.of(anioVencimiento, mesVencimiento, diaAdministracion);

                    if(pagoDB.getTipoPago().getTipo().equals("Arriendo")) {

                        // Calcular valor corregido a pagar según la fecha de pago para el caso arriendo
                        Float valorConAjuste = (pagoDTO.getFechaPago().isAfter(fechaArriendo))
                                ? (pagoDB.getPrecio() * 1.05F) : (pagoDB.getPrecio() * 1F);

                        EstadoPago estadoPago;

                        if(pagoDTO.getValorPagado().compareTo(valorConAjuste) == 0) {

                            estadoPago = estadoPagoRepository.findByEstado("Pagado").orElse(null);

                        } else {

                            estadoPago = estadoPagoRepository.findByEstado("Pendiente").orElse(null);

                        }

                        if(estadoPago != null) {

                            // Actualizar pago con los valores nuevos
                            pagoDB.setEstadoPago(estadoPago);
                            pagoDB.setUsuario(usuarioDB);
                            pagoDB.setEmpresa(empresaDB);
                            pagoDB.setFechaPago(pagoDTO.getFechaPago());
                            pagoDB.setValorPagado(pagoDTO.getValorPagado());
                            pagoDB.setSoporte(pagoDTO.getSoporte());

                            return pagoMapper.pagoToPagoDTO(pagoRepository.save(pagoDB));

                        } else {

                            return null;

                        }

                    } else if (pagoDB.getTipoPago().getTipo().equals("Admon")) {

                        // Calcular valor corregido a pagar según la fecha de pago para el caso administración
                        Float valorConAjuste = (pagoDTO.getFechaPago().isBefore(fechaAdministracion))
                                ? (pagoDB.getPrecio() * 0.88F) : (pagoDB.getPrecio() * 1F);

                        EstadoPago estadoPago;

                        if(pagoDTO.getValorPagado().compareTo(valorConAjuste) == 0) {

                            estadoPago = estadoPagoRepository.findByEstado("Pagado").orElse(null);

                        } else {

                            estadoPago = estadoPagoRepository.findByEstado("Pendiente").orElse(null);

                        }

                        if(estadoPago != null) {

                            // Actualizar pago con los valores nuevos
                            pagoDB.setEstadoPago(estadoPago);
                            pagoDB.setUsuario(usuarioDB);
                            pagoDB.setEmpresa(empresaDB);
                            pagoDB.setFechaPago(pagoDTO.getFechaPago());
                            pagoDB.setValorPagado(pagoDTO.getValorPagado());
                            pagoDB.setSoporte(pagoDTO.getSoporte());

                            return pagoMapper.pagoToPagoDTO(pagoRepository.save(pagoDB));

                        } else {

                            return null;

                        }

                    } else if (pagoDB.getTipoPago().getTipo().equals("Extra")) {

                        EstadoPago estadoPago;

                        if(pagoDTO.getValorPagado().compareTo(pagoDB.getPrecio()) == 0) {

                            estadoPago = estadoPagoRepository.findByEstado("Pagado").orElse(null);

                        } else {

                            estadoPago = estadoPagoRepository.findByEstado("Pendiente").orElse(null);

                        }

                        if(estadoPago != null) {

                            // Actualizar pago con los valores nuevos
                            pagoDB.setEstadoPago(estadoPago);
                            pagoDB.setUsuario(usuarioDB);
                            pagoDB.setEmpresa(empresaDB);
                            pagoDB.setFechaPago(pagoDTO.getFechaPago());
                            pagoDB.setValorPagado(pagoDTO.getValorPagado());
                            pagoDB.setSoporte(pagoDTO.getSoporte());

                            return pagoMapper.pagoToPagoDTO(pagoRepository.save(pagoDB));

                        } else {

                            return null;

                        }

                    } else {

                        return null;

                    }

                } else {

                    throw new Exception();

                }

            } else {

                return null;

            }

        } catch (Exception e) {

            throw new Exception();

        }

    }

    /**
     * Método para crear pagos de forma masiva subiendo un archivo CSV
     * @param archivo Archivo a subir
     * @return Lista de pagos creados
     */
    @Override
    public List<PagoDTO> createPagosCSV(MultipartFile archivo) throws Exception {

        try {

            // Leer archivo CSV y recorrer cada una de sus filas
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(archivo.getInputStream(), "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withNullString(""));

            List<Pago> pagos = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                Pago pago = new Pago(
                        null,
                        (csvRecord.get("DESCRIPCION") != null) ? csvRecord.get("DESCRIPCION") : null,
                        (csvRecord.get("PRECIO") != null) ? Float.parseFloat(csvRecord.get("PRECIO")) : null,
                        (csvRecord.get("FECHA_VENCIMIENTO") != null) ? LocalDate.parse(csvRecord.get("FECHA_VENCIMIENTO")) : null,
                        (csvRecord.get("SOPORTE") != null) ? csvRecord.get("SOPORTE") : null,
                        (csvRecord.get("VALOR_PAGADO") != null) ? Float.parseFloat(csvRecord.get("VALOR_PAGADO")) : null,
                        (csvRecord.get("FECHA_PAGO") != null) ? LocalDate.parse(csvRecord.get("FECHA_PAGO")) : null,
                        (csvRecord.get("ID_TIPO_PAGO") != null) ? tipoPagoRepository.findById(Integer.parseInt(csvRecord.get("ID_TIPO_PAGO"))).orElse(null) : null,
                        (csvRecord.get("ID_ESTADO_PAGO") != null) ? estadoPagoRepository.findById(Integer.parseInt(csvRecord.get("ID_ESTADO_PAGO"))).orElse(null) : null,
                        (csvRecord.get("ID_LOCAL") != null) ? localRepository.findById(Long.parseLong(csvRecord.get("ID_LOCAL"))).orElse(null) : null,
                        (csvRecord.get("ID_EMPRESA") != null) ? empresaRepository.findById(Long.parseLong(csvRecord.get("ID_EMPRESA"))).orElse(null) : null,
                        (csvRecord.get("ID_USUARIO") != null) ? usuarioRepository.findById(Long.parseLong(csvRecord.get("ID_USUARIO"))).orElse(null) : null
                );

                pagos.add(pago);

            }

            List<Pago> pagosGuardados = new ArrayList<>();

            // Guardar cada uno de los pagos leídos del archivo CSV
            for(int i=0; i<pagos.size(); i++) {

                try {

                    Pago pagoGuardado = pagoRepository.save(pagos.get(i));

                    pagosGuardados.add(pagoGuardado);

                } catch(DataIntegrityViolationException e){

                    continue;

                }

            }

            return pagoMapper.pagoToPagoDTOList(pagosGuardados);

        } catch (Exception e) {

            throw new Exception();

        }

    }

}