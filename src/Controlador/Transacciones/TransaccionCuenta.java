package Controlador.Transacciones;

import Controlador.Cuenta;
import Controlador.Transaccion;

import java.sql.Date;

public class TransaccionCuenta extends Transaccion {
    private Long cuentaOrigen;
    private Long cuentaDestino;
    public TransaccionCuenta(Double monto, Long origen, Long destino){
        super(monto);
        this.cuentaOrigen = origen;
        this.cuentaDestino = destino;
        super.tipoTransaccion = 1;
    }

    public TransaccionCuenta(Long cuentaOrigen, Long cuentaDestino) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public TransaccionCuenta(Date fecha, Double monto, Long cuentaOrigen, Long cuentaDestino) {
        super(fecha, monto);
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public TransaccionCuenta() {
    }


    public Long getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Long cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Long getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Long cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public String toString() {
        return "TransaccionCuenta{" +
                "cuentaOrigen=" + cuentaOrigen +
                ", cuentaDestino=" + cuentaDestino +
                ", id=" + id +
                ", fecha=" + fecha +
                ", monto=" + monto +
                ", tipoTransaccion=" + tipoTransaccion +
                '}';
    }
}
