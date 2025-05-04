package reporte;

import java.util.List;

import modelo.Transaccion;

public class ReporteAnual extends Reporte {
    public String generarResumenAnual() {
        return "Resumen anual";
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTransacciones'");
    }
}
