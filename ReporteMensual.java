package reporte;

import java.util.List;

import modelo.Transaccion;

public class ReporteMensual extends Reporte {
    public String generarResumenMensual() {
        return "Resumen mensual";
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTransacciones'");
    }
}
