package com.sistemafinanciero.service;

import com.sistemafinanciero.model.Empleado;
import com.sistemafinanciero.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Optional<Empleado> actualizarEmpleado(Long id, Empleado empleadoDetalles) {
        return empleadoRepository.findById(id)
            .map(empleado -> {
                empleado.setNombre(empleadoDetalles.getNombre());
                empleado.setApellido(empleadoDetalles.getApellido());
                empleado.setEstadoContrato(empleadoDetalles.getEstadoContrato());
                empleado.setCuentaEmpresarial(empleadoDetalles.getCuentaEmpresarial());
                return empleadoRepository.save(empleado);
            });
    }

    public boolean eliminarEmpleado(Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Empleado> obtenerEmpleadosPorUsuario(Long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'obtenerEmpleadosPorUsuario'");
    }
}
