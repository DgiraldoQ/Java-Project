package com.sistemafinanciero.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import jakarta.persistence.*;
import java.math.BigDecimal;

import com.sistemafinanciero.util.TipoTransaccion;



@Entity
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // Se persiste en la base de datos
    private BigDecimal monto; // Se persiste en la base de datos
    private String motivo; // Se persiste en la base de datos

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    // Agregar la relación con Reporte
    @ManyToOne
    @JoinColumn(name = "reporte_id") // Nombre de la columna en la base de datos
    private Reporte reporte; // Nueva propiedad que representa la relación con Reporte

    // Propiedades observables (solo para la UI, no se persisten en la base de datos)
    @Transient
    private final StringProperty tipoProperty = new SimpleStringProperty(this, "tipo");

    @Transient
    private final StringProperty usuarioNombreProperty = new SimpleStringProperty(this, "usuarioNombre");

    @Transient
    private final ObjectProperty<BigDecimal> montoProperty = new SimpleObjectProperty<>(this, "monto", BigDecimal.ZERO); // Usar ObjectProperty para BigDecimal

    public Transaccion() {
    }

    // Constructor con parámetros (sin propiedades JavaFX)
    public Transaccion(String tipo, BigDecimal monto, String motivo) {
        this.tipo = tipo;
        this.monto = monto;
        this.motivo = motivo;

        // Inicializamos las propiedades observables (solo para la UI)
        this.tipoProperty.set(tipo);
        this.montoProperty.set(monto); // Establecemos el monto en la propiedad observable
    }

    // Constructor con parámetros adicionales (usuario)
    public Transaccion(String tipo, BigDecimal monto, String motivo, Usuario usuario) {
        this.tipo = tipo;
        this.monto = monto;
        this.motivo = motivo;
        this.usuario = usuario;

        // Inicializamos las propiedades observables (solo para la UI)
        this.tipoProperty.set(tipo);
        this.montoProperty.set(monto); // Establecemos el monto en la propiedad observable
        if (usuario != null) {
            this.usuarioNombreProperty.set(usuario.getNombre());
        }
    }

    // Métodos getter y setter para la persistencia

    public Transaccion(TipoTransaccion type, BigDecimal transactionAmount, String reason) {

        throw new UnsupportedOperationException("Este constructor aún no está implementado.");

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
        this.tipoProperty.set(tipo); // Actualizamos la propiedad observable
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
        this.montoProperty.set(monto); // Actualizamos la propiedad observable
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if (usuario != null) {
            this.usuarioNombreProperty.set(usuario.getNombre());
        } else {
            this.usuarioNombreProperty.set(""); // O lo que prefieras cuando el usuario sea null
        }
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    // Getter y Setter para la nueva propiedad 'reporte'
    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    // Propiedades observables para la UI (no se persisten en la base de datos)

    public StringProperty tipoProperty() {
        return tipoProperty;
    }

    public StringProperty usuarioNombreProperty() {
        return usuarioNombreProperty;
    }

    public ObjectProperty<BigDecimal> montoProperty() {
        return montoProperty; // Ahora retorna ObjectProperty<BigDecimal>
    }
}