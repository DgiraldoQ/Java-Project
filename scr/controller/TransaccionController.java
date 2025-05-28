package com.sistemafinanciero.controller;

import com.sistemafinanciero.model.Transaccion;
import com.sistemafinanciero.model.Usuario;
import com.sistemafinanciero.service.TransaccionService;
import com.sistemafinanciero.service.UsuarioService;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.Optional;

@SuppressWarnings("unused")
@Controller
public class TransaccionController {

    @FXML
    private TableView<Transaccion> transaccionesTable; // Tabla de transacciones
    @FXML
    private TableColumn<Transaccion, String> tipoColumn; // Columna de tipo
    @FXML
    private TableColumn<Transaccion, BigDecimal> montoColumn; // Columna de monto
    @FXML
    private TableColumn<Transaccion, String> usuarioColumn; // Columna de usuario
    @FXML
    private Button btnRefresh; // Botón de refrescar
    @FXML
    private Pane detallesPane; // Panel de detalles
    @FXML
    private Label lblDetalleTransaccion; // Label para detalles de transacción
    @FXML
    private Label lblDetalleUsuario; // Label para detalles de usuario

    private ObservableList<Transaccion> transaccionesList = FXCollections.observableArrayList();
    private final TransaccionService transaccionService;
    private final UsuarioService usuarioService;

    // Constructor con inyección de dependencias (Spring se encarga de la instancia)
    public TransaccionController(TransaccionService transaccionService, UsuarioService usuarioService) {
        this.transaccionService = transaccionService;
        this.usuarioService = usuarioService;
    }

    // Método de inicialización que se ejecuta cuando el controlador se carga
    @FXML
    public void initialize() {
        tipoColumn.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());

        montoColumn.setCellValueFactory(cellData -> cellData.getValue().montoProperty()); // Esto ahora devuelve BigDecimalProperty

        usuarioColumn.setCellValueFactory(cellData -> cellData.getValue().usuarioNombreProperty());

        loadTransacciones();

        btnRefresh.setOnAction(event -> loadTransacciones());

        transaccionesTable.setOnMouseClicked(this::handleRowClick);
    }

    private void loadTransacciones() {
        ObservableList<Transaccion> transacciones = FXCollections.observableArrayList(transaccionService.listar());

        transaccionesList.clear();
        transaccionesList.addAll(transacciones);

        transaccionesTable.setItems(transaccionesList);
    }

    private void handleRowClick(MouseEvent event) {
        Transaccion transaccionSeleccionada = transaccionesTable.getSelectionModel().getSelectedItem();

        if (transaccionSeleccionada != null) {
            lblDetalleTransaccion.setText("Tipo: " + transaccionSeleccionada.getTipo() +
                                          "\nMonto: " + transaccionSeleccionada.getMonto() +
                                          "\nMotivo: " + transaccionSeleccionada.getMotivo());

            // Obtener detalles del usuario relacionado
            Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(transaccionSeleccionada.getUsuario().getId());
            if (usuario.isPresent()) {
                lblDetalleUsuario.setText("Usuario: " + usuario.get().getNombre());
            } else {
                lblDetalleUsuario.setText("Usuario no encontrado");
            }

            // Mostrar el panel de detalles
            detallesPane.setVisible(true);
        } else {
            // Si no se seleccionó ninguna fila, ocultamos los detalles
            detallesPane.setVisible(false);
        }
    }

    // Método para obtener y mostrar las transacciones por usuario
    public void obtenerTransaccionesPorUsuario(Long usuarioId) {
        // Obtener lista de transacciones por usuario desde el servicio
        @SuppressWarnings("unchecked")
        ObservableList<Transaccion> transaccionesPorUsuario = FXCollections.observableArrayList(transaccionService.listarPorUsuario(usuarioId));

        // Limpiar y añadir las transacciones por usuario a la lista observada
        transaccionesList.clear();
        transaccionesList.addAll(transaccionesPorUsuario);

        // Asignar las transacciones a la tabla
        transaccionesTable.setItems(transaccionesList);
    }
}