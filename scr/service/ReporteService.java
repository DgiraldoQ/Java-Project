package com.sistemafinanciero.service;

import com.sistemafinanciero.model.Reporte;
import com.sistemafinanciero.repository.ReporteRepository;
import com.sistemafinanciero.util.TipoReporte;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    private final ReporteRepository reporteRepository;

    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    public List<Reporte> obtenerTodos() {
        return reporteRepository.findAll();
    }

    public Optional<Reporte> obtenerPorId(Long id) {
        return reporteRepository.findById(id);
    }

    public Reporte guardarReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public boolean eliminarReporte(Long id) {
        if (reporteRepository.existsById(id)) {
            reporteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Reporte actualizarReporte(Long id, Reporte reporte) {
        return reporteRepository.findById(id)
            .map(r -> {
                r.setTransacciones(reporte.getTransacciones());
                r.setIngresos(reporte.getIngresos());
                r.setGastos(reporte.getGastos());
                r.setSaldoFinal(reporte.getSaldoFinal());
                r.setFechaInicio(reporte.getFechaInicio());
                r.setFechaFin(reporte.getFechaFin());
                return reporteRepository.save(r);
            })
            .orElseThrow(() -> new RuntimeException("Reporte no encontrado con id " + id));
    }

    
    public ObservableList<Reporte> obtenerTodosReportes() {
        List<Reporte> listaReportes = reporteRepository.findAll();
        return FXCollections.observableArrayList(listaReportes);
    }

    public List<Reporte> generarReportePorTipo(TipoReporte tipoReporteSeleccionado) {
        
        throw new UnsupportedOperationException("Unimplemented method 'generarReportePorTipo'");
    }
}