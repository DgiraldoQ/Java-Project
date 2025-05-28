package com.sistemafinanciero.service;

import com.sistemafinanciero.model.Transaccion;
import com.sistemafinanciero.repository.TransaccionRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    private final TransaccionRepository repository;

    public TransaccionService(TransaccionRepository repository) {
        this.repository = repository;
    }

    /**
     * Listar todas las transacciones.
     *
     * @return una lista observable de transacciones.
     */
    public ObservableList<Transaccion> listar() {
        List<Transaccion> transacciones = repository.findAll();
        return FXCollections.observableArrayList(transacciones);
    }

    /**
     * Obtener una transacción por su ID.
     *
     * @param id el ID de la transacción.
     * @return un Optional con la transacción correspondiente.
     */
    public Optional<Transaccion> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    /**
     * Guardar una nueva transacción.
     *
     * @param transaccion la transacción a guardar.
     * @return la transacción guardada.
     */
    public Transaccion guardar(Transaccion transaccion) {
        return repository.save(transaccion);
    }

    /**
     * Eliminar una transacción por su ID.
     *
     * @param id el ID de la transacción a eliminar.
     */
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    /**
     * Guardar una transacción, método redundante para guardar, por si se desea utilizar otro tipo de lógica.
     *
     * @param nuevaTransaccion la nueva transacción a guardar.
     */
    public void guardarTransaccion(Transaccion nuevaTransaccion) {
        repository.save(nuevaTransaccion);
    }

    /**
     * Obtener las transacciones de un usuario por su ID.
     *
     * @param id el ID del usuario.
     * @return una lista observable de las transacciones del usuario.
     */
    public ObservableList<Transaccion> obtenerTransaccionesPorUsuario(Long id) {
        List<Transaccion> transacciones = repository.findByUsuarioId(id);
        return FXCollections.observableArrayList(transacciones);
    }

    /**
     * Eliminar una transacción por su ID.
     *
     * @param id el ID de la transacción a eliminar.
     */
    public void eliminarTransaccion(Long id) {
        repository.deleteById(id);
    }

    /**
     * Listar transacciones por usuario con un Callback.
     *
     * @param usuarioId el ID del usuario.
     * @return un Callback para la lista de transacciones.
     */
    @SuppressWarnings("rawtypes")
    public Callback listarPorUsuario(Long usuarioId) {
        return param -> obtenerTransaccionesPorUsuario(usuarioId);
    }

    /**
     * Listar todas las transacciones sin filtros.
     *
     * @return una lista de todas las transacciones.
     */
    public List<Transaccion> listarTransacciones() {
        return repository.findAll();
    }
}
