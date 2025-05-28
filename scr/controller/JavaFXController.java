package com.sistemafinanciero.controller;

import com.sistemafinanciero.model.*;
import com.sistemafinanciero.service.*;
import com.sistemafinanciero.util.Rol;
import com.sistemafinanciero.util.TipoTransaccion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class JavaFXController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(JavaFXController.class);

    private static final String ERROR_TITLE = "Error";
    private static final String SUCCESS_TITLE = "Éxito";

    // Campos de Usuario
    @FXML private TextField userNameField;
    @FXML private TextField userLastNameField;
    @FXML private TextField userRutField;
    @FXML private ComboBox<String> accountTypeComboBox;
    @FXML private ComboBox<Rol> userRoleComboBox;
    @FXML private TextField companyNameField;
    @FXML private TableView<Usuario> userTableView;
    @FXML private TableColumn<Usuario, String> userNameColumn;
    @FXML private TableColumn<Usuario, String> userAccountTypeColumn;
    @FXML private TableColumn<Usuario, String> userRutColumn;

    // Campos de Empleado
    @FXML private TextField employeeNameField;
    @FXML private TextField employeeLastNameField;
    @FXML private TextField employeePositionField;
    @FXML private TextField employeeSalaryField;
    @FXML private TableView<Empleado> employeeTableView;
    @FXML private TableColumn<Empleado, String> employeeNameColumn;
    @FXML private TableColumn<Empleado, String> employeePositionColumn;
    @FXML private TableColumn<Empleado, Double> employeeSalaryColumn;
    @FXML private TableColumn<Empleado, String> employeeCompanyColumn;

    // Campos de Transacción
    @FXML private TextField transactionAmountField;
    @FXML private ComboBox<TipoTransaccion> transactionTypeComboBox;
    @FXML private TextField transactionReasonField;
    @FXML private TableView<Transaccion> transactionTableView;
    @FXML private TableColumn<Transaccion, String> transactionTypeColumn;
    @FXML private TableColumn<Transaccion, BigDecimal> transactionAmountColumn;
    @FXML private TableColumn<Transaccion, String> transactionReasonColumn;
    @FXML private TableColumn<Transaccion, String> transactionUserColumn;

    private final UsuarioService usuarioService;
    private final EmpleadoService empleadoService;
    private final TransaccionService transaccionService;
    @SuppressWarnings("unused")
    private final ReporteService reporteService;

    public JavaFXController(UsuarioService usuarioService, EmpleadoService empleadoService,
                            TransaccionService transaccionService, ReporteService reporteService) {
        this.usuarioService = usuarioService;
        this.empleadoService = empleadoService;
        this.transaccionService = transaccionService;
        this.reporteService = reporteService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountTypeComboBox.setItems(FXCollections.observableArrayList("Personal", "Empresarial"));
        userRoleComboBox.setItems(FXCollections.observableArrayList(Rol.values()));
        transactionTypeComboBox.setItems(FXCollections.observableArrayList(TipoTransaccion.values()));

        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        userAccountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("tipoCuenta"));
        userRutColumn.setCellValueFactory(new PropertyValueFactory<>("rut"));

        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        employeePositionColumn.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        employeeSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("sueldo"));
        employeeCompanyColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getUsuario() != null) {
                return new SimpleStringProperty(cellData.getValue().getUsuario().getNombre());
            }
            return new SimpleStringProperty("");
        });

        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        transactionAmountColumn.setCellValueFactory(new PropertyValueFactory<>("monto"));
        transactionReasonColumn.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        transactionUserColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getUsuario() != null) {
                return new SimpleStringProperty(cellData.getValue().getUsuario().getNombre());
            }
            return new SimpleStringProperty("");
        });

        refreshUserTable();
        refreshEmployeeTable(null);
        refreshTransactionTable();
    }

    private void refreshUserTable() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        userTableView.setItems(FXCollections.observableArrayList(usuarios));
        logger.info("Tabla de usuarios actualizada.");
    }

    private void refreshEmployeeTable(Usuario usuario) {
        if (usuario != null) {
            List<Empleado> empleados = empleadoService.obtenerEmpleadosPorUsuario(usuario.getId());
            employeeTableView.setItems(FXCollections.observableArrayList(empleados));
            logger.info("Tabla de empleados actualizada para el usuario: {}", usuario.getNombre());
        }
    }

    private void refreshTransactionTable() {
        List<Transaccion> transactions = transaccionService.listarTransacciones();
        transactionTableView.setItems(FXCollections.observableArrayList(transactions));
        logger.info("Tabla de transacciones actualizada.");
    }

    @FXML
    public void selectUser(MouseEvent event) {
        Usuario selected = userTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            userNameField.setText(selected.getNombre());
            userLastNameField.setText(selected.getApellido());
            userRutField.setText(selected.getRut());
            accountTypeComboBox.setValue(selected.getTipoCuenta());
            userRoleComboBox.setValue(selected.getRol());
            companyNameField.setText(selected.getEmpresa());
            refreshEmployeeTable(selected);
        }
    }

    @FXML
    public void createUser() {
        try {
            String name = userNameField.getText();
            String lastName = userLastNameField.getText();
            String rut = userRutField.getText();
            String accountType = accountTypeComboBox.getValue();
            Rol role = userRoleComboBox.getValue();
            String companyName = companyNameField.getText();

            Usuario newUser = new Usuario(name, lastName, rut, accountType, role, companyName);
            usuarioService.guardarUsuario(newUser);
            refreshUserTable();
            clearUserFields();
            showAlert(SUCCESS_TITLE, "Usuario creado exitosamente.");
        } catch (Exception e) {
            showAlert(ERROR_TITLE, "Error al crear usuario: " + e.getMessage());
        }
    }

    @FXML
    public void deleteUser() {
        Usuario selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert(ERROR_TITLE, "Seleccione un usuario para eliminar.");
            return;
        }
        usuarioService.eliminarUsuario(selectedUser.getId());
        refreshUserTable();
        showAlert(SUCCESS_TITLE, "Usuario eliminado exitosamente.");
    }

    private void clearUserFields() {
        userNameField.clear();
        userLastNameField.clear();
        userRutField.clear();
        accountTypeComboBox.getSelectionModel().clearSelection();
        userRoleComboBox.getSelectionModel().clearSelection();
        companyNameField.clear();
    }

    @FXML
    public void createEmployee() {
        try {
            String name = employeeNameField.getText();
            String lastName = employeeLastNameField.getText();
            String position = employeePositionField.getText();
            double salary = Double.parseDouble(employeeSalaryField.getText());
            Usuario selectedUser = userTableView.getSelectionModel().getSelectedItem();

            if (selectedUser == null) {
                showAlert(ERROR_TITLE, "Debe seleccionar un usuario para asociar el empleado.");
                return;
            }

            Empleado newEmployee = new Empleado(name, lastName, position, salary, position, selectedUser);
            empleadoService.guardarEmpleado(newEmployee);
            refreshEmployeeTable(selectedUser);
            clearEmployeeFields();
            showAlert(SUCCESS_TITLE, "Empleado creado exitosamente.");
        } catch (Exception e) {
            showAlert(ERROR_TITLE, "Error al crear empleado: " + e.getMessage());
        }
    }

    @FXML
    public void deleteEmployee() {
        Empleado selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            showAlert(ERROR_TITLE, "Seleccione un empleado para eliminar.");
            return;
        }
        empleadoService.eliminarEmpleado(selectedEmployee.getId());
        Usuario selectedUser = userTableView.getSelectionModel().getSelectedItem();
        refreshEmployeeTable(selectedUser);
        showAlert(SUCCESS_TITLE, "Empleado eliminado exitosamente.");
    }

    private void clearEmployeeFields() {
        employeeNameField.clear();
        employeeLastNameField.clear();
        employeePositionField.clear();
        employeeSalaryField.clear();
    }

    @FXML
    public void createTransaction() {
        try {
            String amount = transactionAmountField.getText();
            String reason = transactionReasonField.getText();
            TipoTransaccion type = transactionTypeComboBox.getValue();

            if (amount.isEmpty() || reason.isEmpty() || type == null) {
                showAlert(ERROR_TITLE, "Complete todos los campos de la transacción.");
                return;
            }

            BigDecimal transactionAmount = new BigDecimal(amount);
            Transaccion newTransaction = new Transaccion(type, transactionAmount, reason);
            transaccionService.guardarTransaccion(newTransaction);
            refreshTransactionTable();
            clearTransactionFields();
            showAlert(SUCCESS_TITLE, "Transacción creada exitosamente.");
        } catch (Exception e) {
            showAlert(ERROR_TITLE, "Error al crear transacción: " + e.getMessage());
        }
    }

    @FXML
    public void deleteTransaction() {
        Transaccion selectedTransaction = transactionTableView.getSelectionModel().getSelectedItem();
        if (selectedTransaction == null) {
            showAlert(ERROR_TITLE, "Seleccione una transacción para eliminar.");
            return;
        }
        transaccionService.eliminarTransaccion(selectedTransaction.getId());
        refreshTransactionTable();
        showAlert(SUCCESS_TITLE, "Transacción eliminada exitosamente.");
    }

    private void clearTransactionFields() {
        transactionAmountField.clear();
        transactionReasonField.clear();
        transactionTypeComboBox.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}